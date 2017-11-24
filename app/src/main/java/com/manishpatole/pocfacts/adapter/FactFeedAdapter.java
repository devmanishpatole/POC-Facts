package com.manishpatole.pocfacts.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.manishpatole.pocfacts.R;
import com.manishpatole.pocfacts.model.Row;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Adapter to fact feed list.
 *
 * @author Manish Patole.
 */
public class FactFeedAdapter extends RecyclerView.Adapter<FactFeedAdapter.ViewHolder> {

  private List<Row> mFeedRows;

  public FactFeedAdapter(List<Row> mFeedRows) {
    this.mFeedRows = mFeedRows;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.row_fact_feed, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {

    final Row row = mFeedRows.get(position);
    final Context context = holder.ivFeedImage.getContext();

    setRowTitle(holder.tvTitle, row.getTitle(), context);
    setRowDescription(holder.tvDescription, row.getDescription(), context);

    //Loads the image.
    Picasso.with(context)
        .load(row.getImageHref())
        .error(R.drawable.ic_error).placeholder(R.drawable.ic_loading)
        .into(holder.ivFeedImage);
  }

  /**
   * Sets feeds title.
   *
   * @param tvTitle
   * @param title
   * @param context
   */
  private void setRowTitle(TextView tvTitle, String title, Context context) {
    tvTitle.setText(TextUtils.isEmpty(title) ? context.getString(R.string.no_title) : title);
  }

  /**
   * Sets feeds description.
   *
   * @param tvTitle
   * @param title
   * @param context
   */
  private void setRowDescription(TextView tvTitle, String title, Context context) {
    tvTitle.setText(TextUtils.isEmpty(title) ? context.getString(R.string.no_description) : title);
  }

  @Override
  public int getItemCount() {
    return null != mFeedRows ? mFeedRows.size() : 0;
  }

  public void refreshList(List<Row> rows) {
    this.mFeedRows = rows;
    notifyDataSetChanged();
  }

  class ViewHolder extends RecyclerView.ViewHolder {

    ImageView ivFeedImage;
    TextView tvDescription;
    TextView tvTitle;

    ViewHolder(View view) {
      super(view);

      ivFeedImage = view.findViewById(R.id.iv_image);
      tvDescription = view.findViewById(R.id.tv_description);
      tvTitle = view.findViewById(R.id.tv_title);

    }
  }
}