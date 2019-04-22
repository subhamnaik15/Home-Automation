package door.cyron.house.housedoor.cardSlider;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import door.cyron.house.housedoor.R;

import java.util.ArrayList;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderCard> {

    private final int count;
    private final ArrayList<House> content;
    private final View.OnClickListener listener;

    public SliderAdapter(ArrayList<House> houseArrayList, View.OnClickListener listener) {
        this.content = houseArrayList;
        this.count = content.size();
        this.listener = listener;
    }

    @Override
    public SliderCard onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.layout_slider_card, parent, false);

        if (listener != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(view);
                }
            });
        }

        return new SliderCard(view);
    }

    @Override
    public void onBindViewHolder(SliderCard holder, int position) {
        holder.imageView.setBackgroundResource(content.get(position % content.size()).getPic());
    }

    @Override
    public void onViewRecycled(SliderCard holder) {

    }

    @Override
    public int getItemCount() {
        return count;
    }

    class SliderCard extends RecyclerView.ViewHolder {

        private final ImageView imageView;

        SliderCard(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
