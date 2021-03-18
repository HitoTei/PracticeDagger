package com.example.practicedagger.ui.tile

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.practicedagger.MyApplication
import com.example.practicedagger.data.Todo
import com.example.practicedagger.databinding.FragmentTodoItemBinding
import com.example.practicedagger.ui.TodoListViewModel
import javax.inject.Inject

class MyTodoListRecyclerViewAdapter constructor(
    private val fragment: Fragment,
) : RecyclerView.Adapter<MyTodoListRecyclerViewAdapter.ViewHolder>() {

    @Inject
    lateinit var viewModel: TodoListViewModel

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        Log.d(TAG, "onAttachedToRecyclerView")
        viewModel =
            (fragment.requireActivity().application as MyApplication).appComponent.todoListViewModel()
        viewModel.run {
            allChanged.observe(fragment, {
                viewModel.todoList = it as MutableList<Todo>
                notifyDataSetChanged()
                Log.d(TAG, "onAttachedToRecyclerView: allChanged")
            })
            deletedPosition.observe(fragment, {
                notifyItemRemoved(it)
                Log.d(TAG, "onAttachedToRecyclerView: deleted $it")
            })
            changedPosition.observe(fragment, {
                notifyItemChanged(it)
                Log.d(TAG, "onAttachedToRecyclerView: changed $it")
            })
            insertedPosition.observe(fragment, {
                notifyItemInserted(it)
                Log.d(TAG, "onAttachedToRecyclerView: inserted $it")
            })

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent, viewModel)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = viewModel.todoList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = viewModel.todoList.size

    class ViewHolder private constructor(
        private val binding: FragmentTodoItemBinding,
        private val viewModel: TodoListViewModel
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(todo: Todo) {

            binding.viewModel = TodoItemViewModel(todo, viewModel)
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup, viewModel: TodoListViewModel): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FragmentTodoItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding, viewModel)
            }
        }
    }

    companion object {
        const val TAG = "MyTodoListRecyclerViewAdapter"
    }
}