package com.example.cardviewcore.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cardviewcore.Model.Question;
import com.example.cardviewcore.R;

import java.util.List;

/**
 * Created by ${Murad} on ${10-8-2015}.
 */
public class QuestionsListAdapter extends RecyclerView.Adapter<QuestionsListAdapter.FeedViewHolder>
{
    List<Question> questionsList;
    Context context;


    public class FeedViewHolder extends RecyclerView.ViewHolder
    {
        CardView cardview;
        TextView question;

        public FeedViewHolder(View itemView)
        {
            super(itemView);
            cardview = itemView.findViewById(R.id.cv);
            question = itemView.findViewById(R.id.Question);
        }
    }

    public QuestionsListAdapter(List<Question> questionsList, Context context)
    {this.questionsList = questionsList;
        this.context = context;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        FeedViewHolder feedViewHolder;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_item, parent, false);
        feedViewHolder = new FeedViewHolder(view);
        return feedViewHolder;
    }

    @Override
    public void onBindViewHolder(final FeedViewHolder holder, int position)
    {
        holder.question.setText(questionsList.get(position).getQuestion());
    }

    @Override
    public int getItemCount()
    {return questionsList.size();
    }


}
