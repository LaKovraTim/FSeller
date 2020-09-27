package falabella.lakovratim.android.fastseller.presentation.util

sealed class OrderMenu {
    object SeeMap : OrderMenu()
    object Delivered : OrderMenu()
    object Postpone : OrderMenu()
    object Refuse : OrderMenu()
    object Call : OrderMenu()
    object PayWithQR : OrderMenu()
}