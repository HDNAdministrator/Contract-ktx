package pt.hdn.contract.util

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parceler
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

    //region vars
    val isValid: Boolean; get() = validate()
    //endregion vars

//    companion object : Parceler<Recurrence> {
//        override fun create(parcel: Parcel): Recurrence {
//            return with(parcel) {
//                Recurrence(
//                    start = ZonedDateTime.parse(readString()),
//                    monthType = readInt(),
//                    daysType = readInt(),
//                    finish = readString()?.let { ZonedDateTime.parse(it) },
//                    monthsPeriod = readInt().let { if (it == 1) readInt() else null },
//                    daysPeriod = readInt().let { if (it == 1) readInt() else null },
//                    months = readInt().let { if (it == 1) readArrayList(Int::class.java.classLoader) as MutableList<Int> else null },
//                    days = readInt().let { if (it == 1) readArrayList(Int::class.java.classLoader) as MutableList<Int> else null },
//                    dow = readInt().let { if (it == 1) readArrayList(Int::class.java.classLoader) as MutableList<Int> else null }
//                )
//            }
//        }
//
//        override fun Recurrence.write(parcel: Parcel, flags: Int) {
//            with(parcel) {
//                writeString(start.toString())
//                writeInt(monthType)
//                writeInt(daysType)
//                writeString(finish?.toString())
//                monthsPeriod?.run { writeInt(1); writeInt(this) } ?: writeInt(0)
//                daysPeriod?.run { writeInt(1); writeInt(this) } ?: writeInt(0)
//                months?.run { writeInt(1); writeList(this) } ?: writeInt(0)
//                days?.run { writeInt(1); writeList(this) } ?: writeInt(0)
//                dow?.run { writeInt(1); writeList(this) } ?: writeInt(0)
//            }
//        }
//    }

//    private constructor(start: ZonedDateTime, @MonthType monthType: Int, @DaysType daysType: Int, finish: ZonedDateTime?, @MonthsPeriod monthsPeriod: Int?, @DaysPeriod daysPeriod: Int?, months: MutableList<Int>?, days: MutableList<Int>?, dow: MutableList<Int>?) : this(start, monthType, daysType) {
//        this.finish = finish
//        this.monthsPeriod = monthsPeriod
//        this.daysPeriod = daysPeriod
//        this.months = months
//        this.days = days
//        this.dow = dow
//    }

    fun removeMonth(@Month month: Int) {
        months?.remove(month)
    }

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

    fun removeDay(@Day day: Int) {
        days?.remove(day)
    }

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

    fun removeDow(@DayOfWeek dayOfWeek: Int) {
        dow?.remove(dayOfWeek)
    }

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

    private fun validate(): Boolean {
        return when (daysType) {
            DaysType.DAYS -> !days.isNullOrEmpty()
            DaysType.DOW -> !dow.isNullOrEmpty()
            DaysType.PERIOD -> daysPeriod == null
            else -> false
        } && when (monthsPeriod) {
            MonthType.MONTHS -> !months.isNullOrEmpty()
            MonthType.PERIOD -> monthsPeriod == null
            else -> false
        } && (finish?.run { isAfter(start) } ?: true)
    }
}