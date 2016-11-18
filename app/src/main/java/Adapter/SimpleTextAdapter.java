package Adapter;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kiy.app.test_project.R;

import java.util.ArrayList;

import Bean.NotiViewVo;

/**
 * @author KIY.
 * @Since 2016. 11. 18..
 */

public class SimpleTextAdapter extends RecyclerView.Adapter {
	private ArrayList<NotiViewVo> mItems;
	private Context mContext;

	public SimpleTextAdapter(Context mContext) {
		this.mContext = mContext;
	}

	public void setItems(ArrayList<NotiViewVo> rows){
		mItems = rows;
	}

	public void addItem(NotiViewVo row) {
		if (mItems == null) {
			mItems = new ArrayList<>();
		}
		mItems.add(row);
	}

	public void addItems(ArrayList<NotiViewVo> rows) {
		if (mItems == null) {
			mItems = new ArrayList<>();
		}

		mItems.addAll(rows);
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new SimpleTextViewHolder(LayoutInflater.from(mContext).inflate(R.layout.row_simple_text,parent, false));
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		if ( holder instanceof SimpleTextViewHolder ) {
			((SimpleTextViewHolder)holder).context = mContext;
			((SimpleTextViewHolder)holder).mItems = mItems.get(position);
			((SimpleTextViewHolder)holder).mTvTitle.setText(mItems.get(position).title);
		}
	}

	@Override
	public int getItemCount() {
		return mItems == null? 0 : mItems.size();
	}

	private static class SimpleTextViewHolder  extends RecyclerView.ViewHolder {
		public Context context;
		public TextView mTvTitle;
		public NotiViewVo mItems;
		public SimpleTextViewHolder(View itemView) {
			super(itemView);

			mTvTitle = (TextView)itemView.findViewById(R.id.tv_title);

			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					try {
						Intent intent = new Intent();
						PendingIntent pendingIntent = mItems.intent;
						pendingIntent.send(context, 0, intent);
					} catch (PendingIntent.CanceledException e) {
						// the stack trace isn't very helpful here.  Just log the exception message.
						System.out.println( "Sending contentIntent failed: " );
					}
				}
			});
		}
	}
}
