package falabella.lakovratim.android.fastseller.presentation.util

sealed class OrderMenu {
    class SeeMap : OrderMenu()
    class Delivered : OrderMenu()
    class Postpone : OrderMenu()
    class Refuse : OrderMenu()
    class Call:OrderMenu()
}