package com.ocean.postermaker.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateFormateUtil {
    public static String convertToNewFormatDDMMM(String dateStr) {
        if (isValidFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", dateStr)) {
            TimeZone utc = TimeZone.getTimeZone("UTC");
            SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            SimpleDateFormat destFormat = new SimpleDateFormat("dd MMM");
            sourceFormat.setTimeZone(utc);

            Date convertedDate = null;
            try {
                convertedDate = sourceFormat.parse(dateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return destFormat.format(convertedDate);
        } else {
            TimeZone utc = TimeZone.getTimeZone("UTC");
            SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            SimpleDateFormat destFormat = new SimpleDateFormat("dd MMM");
            sourceFormat.setTimeZone(utc);

            Date convertedDate = null;
            try {
                convertedDate = sourceFormat.parse(dateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return destFormat.format(convertedDate);
        }
    }

    public static String convertToNewFormatMMMDDYYYY(String dateStr) {
        if (isValidFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", dateStr)) {
            TimeZone utc = TimeZone.getTimeZone("UTC");
            SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            SimpleDateFormat destFormat = new SimpleDateFormat("MMM dd yyyy");
            sourceFormat.setTimeZone(utc);

            Date convertedDate = null;
            try {
                convertedDate = sourceFormat.parse(dateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return destFormat.format(convertedDate);
        } else {
            TimeZone utc = TimeZone.getTimeZone("UTC");
            SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            SimpleDateFormat destFormat = new SimpleDateFormat("MMM dd yyyy");
            sourceFormat.setTimeZone(utc);

            Date convertedDate = null;
            try {
                convertedDate = sourceFormat.parse(dateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return destFormat.format(convertedDate);
        }
    }

    public static String convertToNewFormatMMMDDYYYY2(String dateStr) {
        if (isValidFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", dateStr)) {
            TimeZone utc = TimeZone.getTimeZone("UTC");
            SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            SimpleDateFormat destFormat = new SimpleDateFormat("MMM dd,yyyy");
            sourceFormat.setTimeZone(utc);

            Date convertedDate = null;
            try {
                convertedDate = sourceFormat.parse(dateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return destFormat.format(convertedDate);
        } else {
            TimeZone utc = TimeZone.getTimeZone("UTC");
            SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            SimpleDateFormat destFormat = new SimpleDateFormat("MMM dd,yyyy");
            sourceFormat.setTimeZone(utc);

            Date convertedDate = null;
            try {
                convertedDate = sourceFormat.parse(dateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return destFormat.format(convertedDate);
        }
    }

    public static boolean isValidFormat(String format, String value) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(value);
            if (!value.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return date != null;
    }

    public static String convertToNewFormatHHMMA(String dateStr) {
        if (isValidFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", dateStr)) {
            TimeZone utc = TimeZone.getTimeZone("UTC");
            SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            SimpleDateFormat destFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
            sourceFormat.setTimeZone(utc);

            Date convertedDate = null;
            try {
                convertedDate = sourceFormat.parse(dateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return destFormat.format(convertedDate);
        } else {
            TimeZone utc = TimeZone.getTimeZone("UTC");
            SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            SimpleDateFormat destFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
            sourceFormat.setTimeZone(utc);

            Date convertedDate = null;
            try {
                convertedDate = sourceFormat.parse(dateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return destFormat.format(convertedDate);
        }
    }

    public static String convertToNewFormat(String dateStr) {
        TimeZone utc = TimeZone.getTimeZone("UTC");
        SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        SimpleDateFormat destFormat = new SimpleDateFormat("dd MMM, hh:mm a");
        sourceFormat.setTimeZone(utc);

        Date convertedDate = null;
        try {
            convertedDate = sourceFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return destFormat.format(convertedDate);
    }

    public static String convertToNewFormatTime(String dateStr) {
        if (isValidFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", dateStr)) {
            TimeZone utc = TimeZone.getTimeZone("UTC");
            SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            SimpleDateFormat destFormat = new SimpleDateFormat("hh : mm a");
            sourceFormat.setTimeZone(utc);

            Date convertedDate = null;
            try {
                convertedDate = sourceFormat.parse(dateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return destFormat.format(convertedDate);
        } else {
            TimeZone utc = TimeZone.getTimeZone("UTC");
            SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            SimpleDateFormat destFormat = new SimpleDateFormat("hh : mm a");
            sourceFormat.setTimeZone(utc);

            Date convertedDate = null;
            try {
                convertedDate = sourceFormat.parse(dateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return destFormat.format(convertedDate);
        }
    }

    public static Date getLocalDate(String date) {
        SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date convertedDate = null;
        try {
            convertedDate = sourceFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertedDate;
    }

    public static Date isValidCheckerDataFormat(String dateStr) {
        if (isValidFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", dateStr)) {
            TimeZone utc = TimeZone.getTimeZone("UTC");
            SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            SimpleDateFormat destFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            sourceFormat.setTimeZone(utc);
            Date convertedDate = null;
            Date outputDate = null;
            try {
                convertedDate = sourceFormat.parse(dateStr);
                String outPut = destFormat.format(convertedDate);
                outputDate = destFormat.parse(outPut);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return outputDate;
        } else {
            TimeZone utc = TimeZone.getTimeZone("UTC");
            SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            sourceFormat.setTimeZone(utc);

            Date convertedDate = null;
            try {
                convertedDate = sourceFormat.parse(dateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return convertedDate;
        }
    }

    public static long convertToNewFormat3(String dateStr) {
        if (isValidFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", dateStr)) {
            TimeZone utc = TimeZone.getTimeZone("UTC");
            SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            SimpleDateFormat destFormat = new SimpleDateFormat("dd MMM, hh:mm a");
            sourceFormat.setTimeZone(utc);

            Date convertedDate = null;
            long timeinMills = 0;
            try {
                convertedDate = sourceFormat.parse(dateStr);
                timeinMills = convertedDate.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return timeinMills;
        } else {
            TimeZone utc = TimeZone.getTimeZone("UTC");
            SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            SimpleDateFormat destFormat = new SimpleDateFormat("dd MMM, hh:mm a");
            sourceFormat.setTimeZone(utc);

            Date convertedDate = null;
            long timeinMills = 0;
            try {
                convertedDate = sourceFormat.parse(dateStr);
                timeinMills = convertedDate.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return timeinMills;
        }
    }

    public static String convertToNewFormat2(String dateStr) {
        if (isValidFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", dateStr)) {
            TimeZone utc = TimeZone.getTimeZone("UTC");
            SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            SimpleDateFormat destFormat = new SimpleDateFormat("dd MMM, hh:mm a");
            sourceFormat.setTimeZone(utc);

            Date convertedDate = null;
            try {
                convertedDate = sourceFormat.parse(dateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return destFormat.format(convertedDate);
        } else {
            TimeZone utc = TimeZone.getTimeZone("UTC");
            SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            SimpleDateFormat destFormat = new SimpleDateFormat("dd MMM, hh:mm a");
            sourceFormat.setTimeZone(utc);

            Date convertedDate = null;
            try {
                convertedDate = sourceFormat.parse(dateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return destFormat.format(convertedDate);
        }
    }

    public static String parseDateToyyyyMMdd(String time) {
        String inputPattern = "yyyy-MM-dd HH:mm";
        String outputPattern = "dd MMM, HH:mm";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String parseDate(Date date) {
        String outputPattern = "dd/MMM/yyyy";
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        String str = null;

        str = outputFormat.format(date);
        return str;
    }

    public static String parseDate2(Date date) {
        String outputPattern = "MMM dd yyyy";
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        String str = null;

        str = outputFormat.format(date);
        return str;
    }

    public static String parseDateToDay(Date date) {
        String outputPattern = "EEE";
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        String str = null;

        str = outputFormat.format(date);
        return str;
    }

    public static String parseSpecialDateToDay(String time) {
        String inputPattern = "dd/MMM/yyyy";
        String outputPattern = "EEE";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String parseDateToDate(Date date) {
        String outputPattern = "dd";
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        String str = null;
        str = outputFormat.format(date);
        return str;
    }

    public static String parseSpecialDateToDate(String time) {
        String inputPattern = "dd/MMM/yyyy";
        String outputPattern = "dd";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

}
