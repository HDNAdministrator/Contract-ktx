package pt.hdn.contract.util

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize
import pt.hdn.contract.annotations.*
import java.time.ZonedDateTime

@Parcelize
data class Recurrence(
    @Expose var start: ZonedDateTime,
    @Expose @MonthType var monthType: Int = MonthType.MONTHS,
    @Expose @DaysType var daysType: Int = DaysType.DAYS,
    @Expose var finish: ZonedDateTime? = null,
    @Expose @MonthsPeriod var monthsPeriod: Int? = null,
    @Expose @DaysPeriod var daysPeriod: Int? = null,
    @Expose private var months: MutableList<Int>? = null,
    @Expose private var days: MutableList<Int>? = null,
    @Expose private var dow: MutableList<Int>? = null
) : Parcelable {

    fun removeMonth(@Month month: Int) { months?.remove(month) }

    fun getMonths(): List<@Month Int>? = months

    fun addMonth(@Month month: Int) {
        months
            ?.apply {
                if (!contains(month)) {
                    add(month)

                    sort()
                }
            } ?: run {
                this.monthType = MonthType.MONTHS
                this.monthsPeriod = null
                this.months = mutableListOf(month)
            }
    }

    fun setMonthPeriod(@MonthsPeriod monthsPeriod: Int) {
        this.monthType = MonthType.PERIOD
        this.monthsPeriod = monthsPeriod
        this.months = null
    }

    fun removeDay(@Day day: Int) { days?.remove(day) }

    fun getDays(): List<@Day Int>? = days

    fun addDay(@Day day: Int) {
        days
            ?.apply {
                if (!contains(day)) {
                    add(day)

                    sort()
                }
            } ?: run {
                this.daysType = DaysType.DAYS
                this.days = mutableListOf(day)
                this.dow = null
                this.daysPeriod = null
            }
    }

    fun setDaysPeriod(@DaysPeriod daysPeriod: Int) {
        this.daysType = DaysType.PERIOD
        this.days = null
        this.dow = null
        this.daysPeriod = daysPeriod
    }

    fun removeDow(@DayOfWeek dayOfWeek: Int) { dow?.remove(dayOfWeek) }

    fun getDow(): List<@DayOfWeek Int>? = dow

    fun addDow(@DayOfWeek dayOfWeek: Int) {
        dow
            ?.apply {
                if (!contains(dayOfWeek)) {
                    add(dayOfWeek)

                    sort()
                }
            } ?: run {
                this.daysType = DaysType.DOW
                this.dow = mutableListOf(dayOfWeek)
                this.days = null
                this.daysPeriod = null
            }
    }

    @Err fun validate(recurrence: Recurrence? = null): Int {
        return when {
           this == recurrence -> Err.NO_CHANGE
            daysType == DaysType.DAYS && days.isNullOrEmpty()-> Err.DAYS
            daysType == DaysType.DOW && dow.isNullOrEmpty() -> Err.DOW
            daysType == DaysType.PERIOD && daysPeriod == null -> Err.DAYS_PERIOD
            monthsPeriod == MonthType.MONTHS && months.isNullOrEmpty() -> Err.MONTHS
            monthsPeriod == MonthType.MONTHS && monthsPeriod == null -> Err.MONTHS_PERIOD
            finish?.run { isBefore(start) } ?: false -> Err.FINISH
            else -> Err.NONE
        }
    }
}