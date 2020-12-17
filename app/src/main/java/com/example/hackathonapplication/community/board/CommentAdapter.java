package com.example.hackathonapplication.community.board;

import android.content.Context;
import android.database.Cursor;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hackathonapplication.R;
import com.example.hackathonapplication.sqlite.BoardDbOpenHelper;
import com.example.hackathonapplication.sqlite.CommentDbOpenHelper;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Comment> commentDataset;
    private Comment data;


    public CommentAdapter(Context context, ArrayList<Comment> commentDataset) {
        this.context = context;
        this.commentDataset = commentDataset;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.community_comment_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        data = commentDataset.get(position);

        holder.iv_profile.setImageResource(R.drawable.im_sample_profile);
        holder.tv_writer.setText(data.getWriter());
        holder.tv_contents.setText(data.getContents());
        holder.tv_date.setText(data.getCommentdate());
        holder.setAdapter(this);
    }

    @Override
    public int getItemCount() {
        return commentDataset.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        private ImageView iv_profile;
        private TextView tv_writer;
        private TextView tv_contents;
        private TextView tv_date;
        private CommentAdapter adapter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_profile = itemView.findViewById(R.id.iv_profile);
            tv_writer = itemView.findViewById(R.id.tv_writer);
            tv_contents = itemView.findViewById(R.id.tv_contents);
            tv_date = itemView.findViewById(R.id.tv_date);
            context = itemView.getContext();
            itemView.setOnCreateContextMenuListener(this);
        }

        public void setAdapter(CommentAdapter adapter) {
            this.adapter = adapter;
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuItem delete = menu.add(Menu.NONE, 1001, 1, "삭제");
            delete.setOnMenuItemClickListener(onClickMenu);
        }

        private final MenuItem.OnMenuItemClickListener onClickMenu = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case 1001: //삭제
                        data = commentDataset.get(getAdapterPosition());                            //adapter로 뿌려질 데이터셋도 같이 관리해줘야한다. 뿌려진 곳에서 위치를 연동해 그 데이터를 삭제
                        CommentDbOpenHelper dbOpenHelper = new CommentDbOpenHelper(context);
                        BoardDbOpenHelper bdbOpenHelper = new BoardDbOpenHelper(context);
                        dbOpenHelper.open();
                        bdbOpenHelper.open();
                        dbOpenHelper.deleteColumn(Integer.valueOf(data.getId()));
                        int commentCount = 0;
                        Cursor bcursor = dbOpenHelper.searchColumns("_id", data.getBoardkey()); //가져오고
                        while (bcursor.moveToNext()) {
                            commentCount = Integer.valueOf(bcursor.getString(bcursor.getColumnIndex("comment")));
                        }
                        commentCount = commentCount - 1;
                        bdbOpenHelper.updateColumnInt("_id",data.getId(),"comment",commentCount);

                        dbOpenHelper.close();
                        bdbOpenHelper.close();
                        commentDataset.remove(getAdapterPosition());                                //데이터셋에서도 지워줘야 화면에서 갱신됨. 안그러면 데이터는 지워도 화면에서 안지워짐
                        notifyDataSetChanged();
                        break;
                }
                return true;
            }
        };
    }

}
