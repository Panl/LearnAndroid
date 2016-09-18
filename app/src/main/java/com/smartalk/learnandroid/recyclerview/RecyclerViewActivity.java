package com.smartalk.learnandroid.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smartalk.learnandroid.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

  RecyclerView recyclerView;
  AnimatorAdapter animatorAdapter;
  List<String> list = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recycler_view);
    recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    animatorAdapter = new AnimatorAdapter(list);
    recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    recyclerView.setAdapter(animatorAdapter);
    initListItems();
  }

  private void initListItems() {
    for (int i = 0; i < 10; i++) {
      list.add(String.valueOf(i));
    }
    animatorAdapter.notifyDataSetChanged();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_recycler, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_insert:
        if (list.size() < 2) {
          return true;
        }
        list.add(1, String.valueOf(100));
        animatorAdapter.notifyItemInserted(1);
        break;
      case R.id.action_remove:
        if (list.size() < 2) {
          return true;
        }
        list.remove(1);
        animatorAdapter.notifyItemRemoved(1);
        break;
      case R.id.action_move:
        if (list.size() < 4) {
          return true;
        }
        list.set(1, "haha");
        animatorAdapter.notifyItemChanged(1);
        //Collections.swap(list, 1, 3);
        String remove = list.remove(1);
        list.add(3, remove);
        animatorAdapter.notifyItemMoved(1, 3);
        break;
      case R.id.action_remove_add:
        if (list.size() < 2) {
          return true;
        }
        list.remove(1);
        list.add("1024");
        animatorAdapter.notifyItemRemoved(1);
        break;
    }
    return super.onOptionsItemSelected(item);
  }
}

class AnimatorAdapter extends RecyclerView.Adapter<AnimatorAdapter.AnimatorHolder> {

  private List<String> list;

  AnimatorAdapter(List<String> list) {
    this.list = list;
  }

  @Override
  public AnimatorHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View animatorItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_animator, parent, false);
    return new AnimatorHolder(animatorItem);
  }

  @Override
  public void onBindViewHolder(AnimatorHolder holder, int position) {
    holder.animatorText.setText(String.format("animator index:%s", list.get(position)));
  }

  @Override
  public int getItemCount() {
    return list.size();
  }

  class AnimatorHolder extends RecyclerView.ViewHolder {
    TextView animatorText;
    AnimatorHolder(View itemView) {
      super(itemView);
      animatorText = (TextView) itemView.findViewById(R.id.animator_text);
    }
  }
}
