package falabella.lakovratim.android.fastseller.presentation.ui.taskdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import falabella.lakovratim.android.fastseller.databinding.FragmentTaskDetailBinding
import falabella.lakovratim.android.fastseller.presentation.util.BaseFragment

class TaskDetailFragment : BaseFragment<FragmentTaskDetailBinding>(){

    override fun setBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTaskDetailBinding = FragmentTaskDetailBinding.inflate(inflater, container, false)

}

