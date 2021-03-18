package com.example.practicedagger.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.practicedagger.MyApplication
import com.example.practicedagger.data.Todo
import com.example.practicedagger.databinding.FragmentTodoItemBinding
import com.example.practicedagger.ui.tile.TodoItemViewModel

class MyTodoListRecyclerViewAdapter(
    private val fragment: Fragment,
) : RecyclerView.Adapter<MyTodoListRecyclerViewAdapter.ViewHolder>() {

    lateinit var viewModel: TodoListViewModel

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        viewModel =
            (fragment.requireActivity().application as MyApplication).appComponent.todoListViewModel()
        viewModel.run {
            allChanged.observe(fragment, Observer {
                viewModel.todoList = it as MutableList<Todo>
                notifyDataSetChanged()
                Log.d(TAG, "onAttachedToRecyclerView: allChanged")
            })
            deletedPosition.observe(fragment, Observer {
                notifyItemRemoved(it)
                Log.d(TAG, "onAttachedToRecyclerView: deleted $it")
            })
            changedPosition.observe(fragment, Observer {
                notifyItemChanged(it)
                Log.d(TAG, "onAttachedToRecyclerView: changed $it")
            })
            insertedPosition.observe(fragment, Observer {
                notifyItemInserted(it)
                Log.d(TAG, "onAttachedToRecyclerView: inserted $it")
            })

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = viewModel.todoList[position]
        holder.bind(item, viewModel)
    }

    override fun getItemCount(): Int = viewModel.todoList.size

    class ViewHolder private constructor(private val binding: FragmentTodoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(todo: Todo, todoListViewModel: TodoListViewModel) {
            binding.viewModel = TodoItemViewModel(todo, todoListViewModel)
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FragmentTodoItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    companion object {
        const val TAG = "MyTodoListRecyclerViewAdapter"
    }
}