package com.example.tieu_nt.mokidemo.Model.LoadMore;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * Created by tieu_nt on 4/23/2018.
 */

public class LoadMoreScroll extends RecyclerView.OnScrollListener{
    int visibleItemCount, totalItemCount = 1;
    int firstVisiblesItems = 0;
    RecyclerView.LayoutManager layoutManager;
    ILoadMore iLoadMore;

    public LoadMoreScroll(RecyclerView.LayoutManager layoutManager, ILoadMore iLoadMore){
        this.layoutManager = layoutManager;
        this.iLoadMore = iLoadMore;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (dy > 0){
            totalItemCount = layoutManager.getItemCount();

            if (layoutManager instanceof LinearLayoutManager){
                visibleItemCount = ((LinearLayoutManager) layoutManager).getChildCount();
                firstVisiblesItems = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
            }else if(layoutManager instanceof GridLayoutManager){
                visibleItemCount = ((GridLayoutManager) layoutManager).getChildCount();
                firstVisiblesItems = ((GridLayoutManager) layoutManager).findFirstVisibleItemPosition();
            }

            Log.d("DEMO", totalItemCount + " : " + firstVisiblesItems + " + " + visibleItemCount);

            if ((visibleItemCount + firstVisiblesItems) == totalItemCount) {
                iLoadMore.loadMore(totalItemCount);
            }
        }
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }
}
