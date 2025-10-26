package org.yascode.interaction_plsql.common.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

public final class DateUtils {

    // ============================================================
    // FORMATS DE DATE COURANTS
    // ============================================================

    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_FORMAT = "HH:mm:ss";
    public static final String ISO_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String FRENCH_DATE_FORMAT = "dd/MM/yyyy";
    public static final String US_DATE_FORMAT = "MM/dd/yyyy";
    public static final String TIMESTAMP_FORMAT = "yyyyMMddHHmmss";

    // Formatters
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern(DATETIME_FORMAT);
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_FORMAT);
    private static final DateTimeFormatter FRENCH_FORMATTER = DateTimeFormatter.ofPattern(FRENCH_DATE_FORMAT);
    private static final DateTimeFormatter US_FORMATTER = DateTimeFormatter.ofPattern(US_DATE_FORMAT);
    private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern(TIMESTAMP_FORMAT);

    // Zone par défaut
    private static final ZoneId DEFAULT_ZONE = ZoneId.systemDefault();

    /**
     * Constructeur privé pour empêcher l'instanciation.
     */
    private DateUtils() {
        throw new AssertionError("Cannot instantiate utility class");
    }

    // ============================================================
    // OBTENIR LA DATE/HEURE ACTUELLE
    // ============================================================

    /**
     * Retourne la date actuelle (sans heure).
     */
    public static LocalDate today() {
        return LocalDate.now();
    }

    /**
     * Retourne la date et l'heure actuelles.
     */
    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    /**
     * Retourne la date et l'heure actuelles avec la zone horaire.
     */
    public static ZonedDateTime nowWithZone() {
        return ZonedDateTime.now(DEFAULT_ZONE);
    }

    /**
     * Retourne l'instant actuel en UTC.
     */
    public static Instant nowInstant() {
        return Instant.now();
    }

    // ============================================================
    // FORMATAGE DE DATES
    // ============================================================

    /**
     * Formate une LocalDate en String (format: yyyy-MM-dd).
     */
    public static String formatDate(LocalDate date) {
        return date != null ? date.format(DATE_FORMATTER) : null;
    }

    /**
     * Formate une LocalDateTime en String (format: yyyy-MM-dd HH:mm:ss).
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.format(DATETIME_FORMATTER) : null;
    }

    /**
     * Formate une date avec un format personnalisé.
     */
    public static String formatDate(LocalDate date, String pattern) {
        if (date == null || pattern == null) {
            return null;
        }
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * Formate une date en format français (dd/MM/yyyy).
     */
    public static String formatDateFrench(LocalDate date) {
        return date != null ? date.format(FRENCH_FORMATTER) : null;
    }

    /**
     * Formate une date en format américain (MM/dd/yyyy).
     */
    public static String formatDateUS(LocalDate date) {
        return date != null ? date.format(US_FORMATTER) : null;
    }

    /**
     * Formate en timestamp (yyyyMMddHHmmss).
     */
    public static String formatTimestamp(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.format(TIMESTAMP_FORMATTER) : null;
    }

    // ============================================================
    // PARSING DE DATES
    // ============================================================

    /**
     * Parse une String en LocalDate (format: yyyy-MM-dd).
     *
     * @throws DateTimeParseException si le format est invalide
     */
    public static LocalDate parseDate(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return null;
        }
        return LocalDate.parse(dateStr, DATE_FORMATTER);
    }

    /**
     * Parse une String en LocalDateTime (format: yyyy-MM-dd HH:mm:ss).
     *
     * @throws DateTimeParseException si le format est invalide
     */
    public static LocalDateTime parseDateTime(String dateTimeStr) {
        if (dateTimeStr == null || dateTimeStr.trim().isEmpty()) {
            return null;
        }
        return LocalDateTime.parse(dateTimeStr, DATETIME_FORMATTER);
    }

    /**
     * Parse une String avec un format personnalisé.
     *
     * @throws DateTimeParseException si le format est invalide
     */
    public static LocalDate parseDate(String dateStr, String pattern) {
        if (dateStr == null || pattern == null) {
            return null;
        }
        return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * Parse de manière sécurisée (retourne null en cas d'erreur).
     */
    public static LocalDate parseDateSafe(String dateStr) {
        try {
            return parseDate(dateStr);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Parse de manière sécurisée avec format personnalisé.
     */
    public static LocalDate parseDateSafe(String dateStr, String pattern) {
        try {
            return parseDate(dateStr, pattern);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    // ============================================================
    // CONVERSIONS
    // ============================================================

    /**
     * Convertit LocalDate en java.util.Date.
     */
    public static Date toDate(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        return Date.from(localDate.atStartOfDay(DEFAULT_ZONE).toInstant());
    }

    /**
     * Convertit LocalDateTime en java.util.Date.
     */
    public static Date toDate(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return Date.from(localDateTime.atZone(DEFAULT_ZONE).toInstant());
    }

    /**
     * Convertit java.util.Date en LocalDate.
     */
    public static LocalDate toLocalDate(Date date) {
        if (date == null) {
            return null;
        }
        return date.toInstant().atZone(DEFAULT_ZONE).toLocalDate();
    }

    /**
     * Convertit java.util.Date en LocalDateTime.
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        if (date == null) {
            return null;
        }
        return date.toInstant().atZone(DEFAULT_ZONE).toLocalDateTime();
    }

    /**
     * Convertit java.sql.Date en LocalDate.
     */
    public static LocalDate toLocalDate(java.sql.Date sqlDate) {
        return sqlDate != null ? sqlDate.toLocalDate() : null;
    }

    /**
     * Convertit LocalDate en java.sql.Date.
     */
    public static java.sql.Date toSqlDate(LocalDate localDate) {
        return localDate != null ? java.sql.Date.valueOf(localDate) : null;
    }

    /**
     * Convertit LocalDateTime en java.sql.Timestamp.
     */
    public static java.sql.Timestamp toSqlTimestamp(LocalDateTime localDateTime) {
        return localDateTime != null ? java.sql.Timestamp.valueOf(localDateTime) : null;
    }

    // ============================================================
    // CALCULS DE DATES
    // ============================================================

    /**
     * Ajoute des jours à une date.
     */
    public static LocalDate addDays(LocalDate date, long days) {
        return date != null ? date.plusDays(days) : null;
    }

    /**
     * Ajoute des mois à une date.
     */
    public static LocalDate addMonths(LocalDate date, long months) {
        return date != null ? date.plusMonths(months) : null;
    }

    /**
     * Ajoute des années à une date.
     */
    public static LocalDate addYears(LocalDate date, long years) {
        return date != null ? date.plusYears(years) : null;
    }

    /**
     * Soustrait des jours à une date.
     */
    public static LocalDate subtractDays(LocalDate date, long days) {
        return date != null ? date.minusDays(days) : null;
    }

    /**
     * Soustrait des mois à une date.
     */
    public static LocalDate subtractMonths(LocalDate date, long months) {
        return date != null ? date.minusMonths(months) : null;
    }

    /**
     * Soustrait des années à une date.
     */
    public static LocalDate subtractYears(LocalDate date, long years) {
        return date != null ? date.minusYears(years) : null;
    }

    /**
     * Calcule le nombre de jours entre deux dates.
     */
    public static long daysBetween(LocalDate start, LocalDate end) {
        if (start == null || end == null) {
            return 0;
        }
        return ChronoUnit.DAYS.between(start, end);
    }

    /**
     * Calcule le nombre de mois entre deux dates.
     */
    public static long monthsBetween(LocalDate start, LocalDate end) {
        if (start == null || end == null) {
            return 0;
        }
        return ChronoUnit.MONTHS.between(start, end);
    }

    /**
     * Calcule le nombre d'années entre deux dates.
     */
    public static long yearsBetween(LocalDate start, LocalDate end) {
        if (start == null || end == null) {
            return 0;
        }
        return ChronoUnit.YEARS.between(start, end);
    }

    /**
     * Calcule l'âge en années à partir d'une date de naissance.
     */
    public static int calculateAge(LocalDate birthDate) {
        if (birthDate == null) {
            return 0;
        }
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    // ============================================================
    // COMPARAISONS DE DATES
    // ============================================================

    /**
     * Vérifie si la date est aujourd'hui.
     */
    public static boolean isToday(LocalDate date) {
        return date != null && date.equals(LocalDate.now());
    }

    /**
     * Vérifie si la date est dans le passé.
     */
    public static boolean isPast(LocalDate date) {
        return date != null && date.isBefore(LocalDate.now());
    }

    /**
     * Vérifie si la date est dans le futur.
     */
    public static boolean isFuture(LocalDate date) {
        return date != null && date.isAfter(LocalDate.now());
    }

    /**
     * Vérifie si une date est entre deux autres dates (inclus).
     */
    public static boolean isBetween(LocalDate date, LocalDate start, LocalDate end) {
        if (date == null || start == null || end == null) {
            return false;
        }
        return !date.isBefore(start) && !date.isAfter(end);
    }

    /**
     * Vérifie si deux dates sont le même jour.
     */
    public static boolean isSameDay(LocalDate date1, LocalDate date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        return date1.equals(date2);
    }

    /**
     * Vérifie si une année est bissextile.
     */
    public static boolean isLeapYear(int year) {
        return Year.of(year).isLeap();
    }

    // ============================================================
    // UTILITAIRES
    // ============================================================

    /**
     * Retourne le premier jour du mois.
     */
    public static LocalDate getFirstDayOfMonth(LocalDate date) {
        return date != null ? date.with(TemporalAdjusters.firstDayOfMonth()) : null;
    }

    /**
     * Retourne le dernier jour du mois.
     */
    public static LocalDate getLastDayOfMonth(LocalDate date) {
        return date != null ? date.with(TemporalAdjusters.lastDayOfMonth()) : null;
    }

    /**
     * Retourne le premier jour de l'année.
     */
    public static LocalDate getFirstDayOfYear(LocalDate date) {
        return date != null ? date.with(TemporalAdjusters.firstDayOfYear()) : null;
    }

    /**
     * Retourne le dernier jour de l'année.
     */
    public static LocalDate getLastDayOfYear(LocalDate date) {
        return date != null ? date.with(TemporalAdjusters.lastDayOfYear()) : null;
    }

    /**
     * Retourne le prochain lundi.
     */
    public static LocalDate getNextMonday(LocalDate date) {
        return date != null ? date.with(TemporalAdjusters.next(DayOfWeek.MONDAY)) : null;
    }

    /**
     * Retourne le nombre de jours dans le mois.
     */
    public static int getDaysInMonth(LocalDate date) {
        return date != null ? date.lengthOfMonth() : 0;
    }

    /**
     * Retourne le nombre de jours dans l'année.
     */
    public static int getDaysInYear(LocalDate date) {
        return date != null ? date.lengthOfYear() : 0;
    }

    /**
     * Retourne le jour de la semaine (MONDAY, TUESDAY, etc.).
     */
    public static DayOfWeek getDayOfWeek(LocalDate date) {
        return date != null ? date.getDayOfWeek() : null;
    }

    /**
     * Vérifie si c'est un week-end (samedi ou dimanche).
     */
    public static boolean isWeekend(LocalDate date) {
        if (date == null) {
            return false;
        }
        DayOfWeek day = date.getDayOfWeek();
        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
    }

    /**
     * Vérifie si c'est un jour de semaine (lundi à vendredi).
     */
    public static boolean isWeekday(LocalDate date) {
        return date != null && !isWeekend(date);
    }

    /**
     * Retourne la date la plus récente entre deux dates.
     */
    public static LocalDate max(LocalDate date1, LocalDate date2) {
        if (date1 == null) return date2;
        if (date2 == null) return date1;
        return date1.isAfter(date2) ? date1 : date2;
    }

    /**
     * Retourne la date la plus ancienne entre deux dates.
     */
    public static LocalDate min(LocalDate date1, LocalDate date2) {
        if (date1 == null) return date2;
        if (date2 == null) return date1;
        return date1.isBefore(date2) ? date1 : date2;
    }

    /**
     * Retourne la date au début de la journée (00:00:00).
     */
    public static LocalDateTime startOfDay(LocalDate date) {
        return date != null ? date.atStartOfDay() : null;
    }

    /**
     * Retourne la date à la fin de la journée (23:59:59).
     */
    public static LocalDateTime endOfDay(LocalDate date) {
        return date != null ? date.atTime(23, 59, 59) : null;
    }

    // ============================================================
    // VALIDATION
    // ============================================================

    /**
     * Vérifie si une String est une date valide.
     */
    public static boolean isValidDate(String dateStr) {
        return parseDateSafe(dateStr) != null;
    }

    /**
     * Vérifie si une String est une date valide selon un format.
     */
    public static boolean isValidDate(String dateStr, String pattern) {
        return parseDateSafe(dateStr, pattern) != null;
    }

    /**
     * Vérifie si une date est valide (non null et dans une plage raisonnable).
     */
    public static boolean isValidDateRange(LocalDate date) {
        if (date == null) {
            return false;
        }
        LocalDate minDate = LocalDate.of(1900, 1, 1);
        LocalDate maxDate = LocalDate.of(2100, 12, 31);
        return !date.isBefore(minDate) && !date.isAfter(maxDate);
    }
}
