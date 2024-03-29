package com.example.architecture;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {

    private List<Note> notes = new ArrayList<>();
    private OnItemCLickListner listner;

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);

        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentNote = notes.get(position);

        holder.tvPriority.setText(String.valueOf(currentNote.getPriority()));
        holder.tvTitle.setText(currentNote.getTitle());
        holder.tvDescription.setText(currentNote.getDescription());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Note> notes){
        this.notes = notes;
        notifyDataSetChanged();
    }

    public Note getNoteAt(int position){
        return notes.get(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder{
        private TextView tvTitle;
        private TextView tvDescription;
        private TextView tvPriority;

        public NoteHolder(View view){
            super(view);

            tvTitle = view.findViewById(R.id.text_view_title);
            tvDescription = view.findViewById(R.id.text_view_description);
            tvPriority = view.findViewById(R.id.text_view_priority);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listner != null && position != RecyclerView.NO_POSITION){
                        listner.onItemClick(notes.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemCLickListner {
        void onItemClick(Note note);
    }

    public void setOnItemClickListner(OnItemCLickListner listner){
        this.listner = listner;
    }
}
