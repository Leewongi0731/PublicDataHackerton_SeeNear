package com.example.hackathonapplication.community.board;

import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hackathonapplication.R;
import com.example.hackathonapplication.sqlite.BoardDbOpenHelper;
import com.example.hackathonapplication.sqlite.CommentDbOpenHelper;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Post> postDataset;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;


    public PostAdapter(Context context,ArrayList<Post> postDataset,FragmentManager fragmentManager) {
        this.context = context;
        this.postDataset = postDataset;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.community_category_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) { // 임시데이터
        Post data = postDataset.get(position);

        holder.iv_profile.setImageResource(R.drawable.im_sample_profile);
        holder.tv_writer.setText(data.getWriter());
        holder.tv_contents.setText(data.getContents());
        holder.tv_date.setText(data.getDate());
        holder.tv_like.setText(data.getLike());
        holder.tv_comment.setText(data.getComment());

        holder.setAdapter(this);
        holder.ll_post.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  Bundle bundle = new Bundle();
                                                  bundle.putString("id", data.getId());
                                                  CommunityCommentFragment ccf = new CommunityCommentFragment();
                                                  ccf.setArguments(bundle);
                                                  replaceFragment(ccf);
                                              }
                                          }
        );
    }

    @Override
    public int getItemCount() {
        return postDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        private LinearLayout ll_post;
        private ImageView iv_profile;
        private TextView tv_writer;
        private TextView tv_contents;
        private TextView tv_date;
        private TextView tv_like;
        private TextView tv_comment;
        private PostAdapter adapter;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ll_post = itemView.findViewById(R.id.ll_post);
            iv_profile = itemView.findViewById(R.id.iv_profile);
            tv_writer = itemView.findViewById(R.id.tv_writer);
            tv_contents = itemView.findViewById(R.id.tv_contents);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_like = itemView.findViewById(R.id.tv_postLikeCount);
            tv_comment = itemView.findViewById(R.id.tv_commentCount);
            itemView.setOnCreateContextMenuListener(this);
        }

        public void setAdapter(PostAdapter adapter) {
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
                        Post data = postDataset.get(getAdapterPosition());                            //adapter로 뿌려질 데이터셋도 같이 관리해줘야한다. 뿌려진 곳에서 위치를 연동해 그 데이터를 삭제
                        BoardDbOpenHelper dbOpenHelper = new BoardDbOpenHelper(context);
                        dbOpenHelper.open();
                        dbOpenHelper.deleteColumn(Integer.valueOf(data.getId()));
                        dbOpenHelper.close();
                        postDataset.remove(getAdapterPosition());                                //데이터셋에서도 지워줘야 화면에서 갱신됨. 안그러면 데이터는 지워도 화면에서 안지워짐
                        notifyDataSetChanged();
                        break;
                }
                return true;
            }
        };
    }

    public void replaceFragment(Fragment fragment) {
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();                                                                       // Fragment로 사용할 MainActivity내의 layout공간을 선택합니다.
    }

}
