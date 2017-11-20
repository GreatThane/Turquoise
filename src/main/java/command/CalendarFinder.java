package command;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ethan on 6/10/17.
 */
public class CalendarFinder {

    public static void showCalendar(MessageReceivedEvent event, MessageChannel channel, String msg, Matcher rateMeMatcherMatcher, User author) {

        if (event.getMessage().getContent().contains("delete event")) {

            final Pattern deletePattern = Pattern.compile(".*?delete\\sevent(.*)");
            Matcher deleteMatcher = deletePattern.matcher(msg);
            String title = deleteMatcher.group(1);
            if (event.getGuild().getName().equalsIgnoreCase("Lila\'s Special Little Friends")) {

            }
        } else {
            final Pattern monthPattern = Pattern.compile("(?:.*?)(Jan\\.?|January|Feb\\.?|February|Mar\\.?|March|Apr\\.?|April|May|Jun\\.?|June|Jul\\.?|July|Aug\\.?|August|Sep\\.?|Sept\\.?|September|Oct\\.?|October|Nov\\.?|November|Dec\\.?|December)(.*)", Pattern.CASE_INSENSITIVE);
            final Pattern dayPattern = Pattern.compile("(?:.*?)([1-9][0-9]?)(.*)", Pattern.CASE_INSENSITIVE);
            final Pattern yearPattern = Pattern.compile("(?:.*?)20([1-9][0-9])(.*)", Pattern.CASE_INSENSITIVE);
            final Pattern mmddyyPattern = Pattern.compile("(?:.*?)([1-9][0-9]?)/([1-9][0-9]?)/(?:20)?([1-9][0-9])(.*)", Pattern.CASE_INSENSITIVE);
            final Pattern timePattern = Pattern.compile("(?:.*?)(1?[0-9]:[1-9][0-9])(.*)", Pattern.CASE_INSENSITIVE);
            final Pattern extrasPattern = Pattern.compile("(?:.*?)title\\s\"(.*?)\"(?:.*?)about\\s\"(.*?)\"(?:.*?)(?:image)?\\s?\"?(.*?)?\"?", Pattern.CASE_INSENSITIVE);

            String month = "0";
            int day = 0;
            String year = "0";
            String time = "0";

            Matcher extrasMatcher = extrasPattern.matcher(msg);
            Matcher timeMatcher = timePattern.matcher(msg);
            Matcher dateMatcher = mmddyyPattern.matcher(msg);
            Matcher monthMatcher = monthPattern.matcher(msg);

            if (monthMatcher.matches()) {

                if (monthMatcher.group(1).equalsIgnoreCase("Jan") || monthMatcher.group(1).equalsIgnoreCase("Jan.")) {
                    month = "January";
                } else if (monthMatcher.group(1).equalsIgnoreCase("Feb") || monthMatcher.group(1).equalsIgnoreCase("Feb.")) {
                    month = "February";
                } else if (monthMatcher.group(1).equalsIgnoreCase("Mar") || monthMatcher.group(1).equalsIgnoreCase("Mar.")) {
                    month = "March";
                } else if (monthMatcher.group(1).equalsIgnoreCase("Apr") || monthMatcher.group(1).equalsIgnoreCase("Apr.")) {
                    month = "April";
                } else if (monthMatcher.group(1).equalsIgnoreCase("Jun") || monthMatcher.group(1).equalsIgnoreCase("Jun.")) {
                    month = "June";
                } else if (monthMatcher.group(1).equalsIgnoreCase("Jul") || monthMatcher.group(1).equalsIgnoreCase("Jul.")) {
                    month = "July";
                } else if (monthMatcher.group(1).equalsIgnoreCase("Aug") || monthMatcher.group(1).equalsIgnoreCase("Aug.")) {
                    month = "August";
                } else if (monthMatcher.group(1).equalsIgnoreCase("Sep") || monthMatcher.group(1).equalsIgnoreCase("Sep.") || monthMatcher.group(1).equalsIgnoreCase("Sept") || monthMatcher.group(1).equalsIgnoreCase("Sept.")) {
                    month = "September";
                } else if (monthMatcher.group(1).equalsIgnoreCase("Oct") || monthMatcher.group(1).equalsIgnoreCase("Oct.")) {
                    month = "October";
                } else if (monthMatcher.group(1).equalsIgnoreCase("Nov") || monthMatcher.group(1).equalsIgnoreCase("Nov.")) {
                    month = "November";
                } else if (monthMatcher.group(1).equalsIgnoreCase("Dec") || monthMatcher.group(1).equalsIgnoreCase("Dec.")) {
                    month = "December";
                }
                Matcher dayMatcher = dayPattern.matcher(monthMatcher.group(2));
                if (dayMatcher.matches()) {
                    day = Integer.parseInt(dayMatcher.group(1));

                    Matcher yearMatcher = yearPattern.matcher(dayMatcher.group(2));
                    if (yearMatcher.matches()) {
                        year = "20" + yearMatcher.group(1);
                    } else {
                        channel.sendMessage(author.getAsMention() + ": You've given an improper date!").complete();
                    }
                } else {
                    channel.sendMessage(author.getAsMention() + ": You've given an improper date!").complete();
                }
            } else if (dateMatcher.matches()) {

                switch (dateMatcher.group(1)) {
                    case "1":
                        month = "January";
                        break;
                    case "2":
                        month = "February";
                        break;
                    case "3":
                        month = "March";
                        break;
                    case "4":
                        month = "April";
                        break;
                    case "5":
                        month = "May";
                        break;
                    case "6":
                        month = "June";
                        break;
                    case "7":
                        month = "July";
                        break;
                    case "8":
                        month = "August";
                        break;
                    case "9":
                        month = "September";
                        break;
                    case "10":
                        month = "October";
                        break;
                    case "11":
                        month = "November";
                        break;
                    case "12":
                        month = "December";
                        break;
                }
                day = Integer.parseInt(dateMatcher.group(2));
                year = "20" + dateMatcher.group(3);
            } else if (!dateMatcher.matches() && !monthMatcher.matches()) {
                channel.sendMessage(author.getAsMention() + ": You need to give a date!").complete();
            }

            if (timeMatcher.matches()) {
                Pattern properTimePattern = Pattern.compile(".*?(1?[0-9]):([1-9][0-9])", Pattern.CASE_INSENSITIVE);
                Matcher properTimeMatcher = properTimePattern.matcher(msg);

                if (Integer.parseInt(properTimeMatcher.group(1)) < 13 && Integer.parseInt(properTimeMatcher.group(2)) < 60) {
                    time = timeMatcher.group(1);
                } else {
                    channel.sendMessage(author.getAsMention() + ": You've given an improper time!").complete();
                }

                //Current Date and Time have been found

                String tempMonth = "0";
                switch (month) {
                    case "January":
                        tempMonth = "01";
                        break;
                    case "February":
                        tempMonth = "02";
                        break;
                    case "March":
                        tempMonth = "03";
                        break;
                    case "April":
                        tempMonth = "04";
                        break;
                    case "May":
                        tempMonth = "05";
                        break;
                    case "June":
                        tempMonth = "06";
                        break;
                    case "July":
                        tempMonth = "07";
                        break;
                    case "August":
                        tempMonth = "08";
                        break;
                    case "September":
                        tempMonth = "09";
                        break;
                    case "October":
                        tempMonth = "10";
                        break;
                    case "November":
                        tempMonth = "11";
                        break;
                    case "December":
                        tempMonth = "12";
                        break;
                }
                String tempDay;
                if (day < 10) {
                    tempDay = "0" + day;
                } else {
                    tempDay = Integer.toString(day);
                }
                String checkDate = year + "/" + tempMonth + "/" + tempDay + " " + time;
                if (CalendarFinder.isFutureDate(checkDate)) {
                    MessageChannel calendarChannel;
                    if (event.getGuild().getName().equalsIgnoreCase("Lila\'s Special Little Friends")) {
                        calendarChannel = event.getGuild().getTextChannelById("341434767360655360");
                    } else if (event.getGuild().getName().equalsIgnoreCase("Sacred Space")) {
                        calendarChannel = event.getGuild().getTextChannelById("260957456639721475");
                    } else {
                        calendarChannel = event.getTextChannel();
                    }

                    //Create Embed for event

                    EmbedBuilder embedBuilder = new EmbedBuilder();
                    embedBuilder.setAuthor(author.getName(), author.getAvatarUrl(), author.getAvatarUrl());
                    embedBuilder.setTitle(extrasMatcher.group(1));
                    embedBuilder.setDescription(extrasMatcher.group(2));
                    if (!extrasMatcher.group(3).isEmpty()) {
                        embedBuilder.setThumbnail(extrasMatcher.group(3));
                    }
                    embedBuilder.addField("When:", month + " " + day + ", " + year + " at " + time, false);
                    embedBuilder.setFooter(year + "/" + tempMonth + "/" + tempDay + " " + time + " " + author.getDiscriminator(), event.getGuild().getSelfMember().getUser().getAvatarUrl());

                    calendarChannel.sendMessage(embedBuilder.build()).complete();
                }

            } else {
                channel.sendMessage(author.getAsMention() + ": You've given an improper time!").complete();
            }

        }
    }

    private static boolean isFutureDate(String checkDate) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        String currentDateTime = dtf.format(now);
        Pattern time = Pattern.compile("(.*?)/(.*?)/(.*?) (.*?):(.*)", Pattern.CASE_INSENSITIVE);
        Matcher currentTime = time.matcher(currentDateTime);
        Matcher targetTime = time.matcher(checkDate);

        int currentYear = Integer.parseInt(currentTime.group(1));
        int currentMonth = Integer.parseInt(currentTime.group(2));
        int currentDay = Integer.parseInt(currentTime.group(3));

        int targetYear = Integer.parseInt(targetTime.group(1));
        int targetMonth = Integer.parseInt(targetTime.group(2));
        int targetDay = Integer.parseInt(targetTime.group(3));

        if (currentYear <= targetYear) {
            if (currentMonth <= targetMonth) {
                if (currentDay <= targetDay) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}

