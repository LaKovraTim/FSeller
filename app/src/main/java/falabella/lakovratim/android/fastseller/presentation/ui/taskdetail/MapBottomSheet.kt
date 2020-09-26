package falabella.lakovratim.android.fastseller.presentation.ui.taskdetail

import android.app.Dialog
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import falabella.lakovratim.android.fastseller.R

class MapBottomSheet : BottomSheetDialogFragment() {


    companion object {
        fun newInstance(): MapBottomSheet =
            MapBottomSheet().apply {
            }
    }

    override fun setupDialog(dialog: Dialog, style: Int) {
        val contentView = View.inflate(context, R.layout.button_sheet_map_dialog, null)
        dialog.setContentView(contentView)
        (contentView.parent as View).setBackgroundColor(
            resources.getColor(
                android.R.color.transparent
            )
        )
    }
}