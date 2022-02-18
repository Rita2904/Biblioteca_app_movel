package com.example.biblioteca.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.biblioteca.Model.SliderImage;
import com.example.biblioteca.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.SliderImageViewHolder>{

    private List<SliderImage> sliderImages;
    private ViewPager2 viewPager2;

    public ImageAdapter(List<SliderImage> sliderImages, ViewPager2 viewPager2) {
        this.sliderImages = sliderImages;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SliderImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderImageViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.image_slider, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SliderImageViewHolder holder, int position) {
        holder.setImage(sliderImages.get(position));
        if(position == sliderImages.size() - 2) {
            viewPager2.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return sliderImages.size();
    }

    class SliderImageViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;

        SliderImageViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.ivSlider);
        }

        void setImage(SliderImage sliderImage) {

            //glide para imagens da api //

            imageView.setImageResource(sliderImage.getImage());
        }
    }

    //slider contínuo
    private Runnable runnable = new Runnable() {
        @SuppressLint("NotifyDataSetChanged")
        @Override
        public void run() {
            sliderImages.addAll(sliderImages);
            notifyDataSetChanged();
        }
    };
}
