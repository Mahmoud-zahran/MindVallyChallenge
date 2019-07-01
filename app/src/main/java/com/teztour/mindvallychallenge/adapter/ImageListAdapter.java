package com.teztour.mindvallychallenge.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.teztour.mindvallychallenge.R;
import com.teztour.mindvallychallenge.entity.Response;
import com.teztour.zahranrxdownloadandcachelib.callbackInterfaces.IDSDownloadDataType;
import com.teztour.zahranrxdownloadandcachelib.models.MDownloadDataType;
import com.teztour.zahranrxdownloadandcachelib.models.MDownloadDataTypeImage;
import com.teztour.zahranrxdownloadandcachelib.utils.DownloadDataTypeServiceProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by development5 on 3/5/2018.
 */

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ViewHolder> implements Filterable {

    List<Response> voucherTransfers;
    android.content.Context Context;
    boolean[] animationStates;
    public static int RestaurantPosition;
    private DownloadDataTypeServiceProvider mProvider;
    List<Response> VoucherTransfersList =null;
    List<Response> filteredVoucherTransfersList = null;
    ImageListAdapter.VoucherTransfersFilter VoucherTransfersFilter;
    public static int VisibleOnce = 0;


    @Override
    public Filter getFilter() {
        if(VoucherTransfersFilter == null)
            VoucherTransfersFilter = new ImageListAdapter.VoucherTransfersFilter(this, VoucherTransfersList);
        return VoucherTransfersFilter;
    }

    //Constractor
    public ImageListAdapter(android.content.Context context, List<Response> VoucherTransfers) {
        Context = context;
        voucherTransfers = VoucherTransfers;
        this.VoucherTransfersList =VoucherTransfers;
        this.filteredVoucherTransfersList = new ArrayList<>();
        animationStates = new boolean[VoucherTransfers.size()];
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView flightLogo;
        public TextView txt_RideId;


        public LinearLayout L_DataShowActivityShadow;
        public LinearLayout l_DataShowActivityBannerContainer;
        //public RelativeLayout R_DataShowActivityDetails;
        public CardView Card_DataShowActivityCard;


        public ViewHolder(final View ConvertView) {
            super(ConvertView);

            flightLogo = (ImageView) ConvertView.findViewById(R.id.Img_DataShowActivityBanner);

            txt_RideId = (TextView) ConvertView.findViewById(R.id.Txt_DataShowActivity_UserName);

            L_DataShowActivityShadow = (LinearLayout) ConvertView.findViewById(R.id.L_DataShowActivityShadow);
            l_DataShowActivityBannerContainer = (LinearLayout) ConvertView.findViewById(R.id.L_DataShowActivityBannerContainer);

//            R_DataShowActivityDetails = (RelativeLayout) ConvertView.findViewById(R.id.R_DataShowActivityDetails);
            Card_DataShowActivityCard = (CardView) ConvertView.findViewById(R.id.Card_DataShowActivityCard);

            if(VisibleOnce == 0) {
                L_DataShowActivityShadow.setVisibility(View.GONE);
                l_DataShowActivityBannerContainer.setVisibility(View.GONE);
            //    R_DataShowActivityDetails.setVisibility(View.GONE);
                flightLogo.setVisibility(View.INVISIBLE);
            }

            VisibleOnce++;



         /*   ConvertView.setOnClickListener(new View.OnClickListener() {
                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View v) {

                   // Shake(flightLogo,Context);
                    Move(Card_ArrivalflightCard , Card_ArrivalflightCard.getWidth(), 0 ,1000);


                    MoveobjectAnimator.addListener(new Animator.AnimatorListener() {
                        public void onAnimationStart(Animator com.teztour.barcodedetect.animation) {}
                        @Override
                        public void onAnimationEnd(Animator com.teztour.barcodedetect.animation) {

                          //  RestaurantPosition = getAdapterPosition();
                        //    Intent intent = new Intent(Context, RestaurantCategoriesActivity.class);
                        //    ConvertView.getContext().startActivity(intent);
                        }
                        public void onAnimationCancel(Animator com.teztour.barcodedetect.animation) {}
                        public void onAnimationRepeat(Animator com.teztour.barcodedetect.animation) {}
                    });

                }
            });*/


        }
    }

    //Create ViewHolder & Intitialize it
    public ImageListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.items_row, viewGroup, false);
        ImageListAdapter.ViewHolder viewHolder = new ImageListAdapter.ViewHolder(v);
        return viewHolder;
    }
    //Fill The List
    public void onBindViewHolder(final ImageListAdapter.ViewHolder Holder, int position) {


        Response response = voucherTransfers.get(position);
        mProvider = DownloadDataTypeServiceProvider.getInstance();
        MDownloadDataType mDataTypeImageCancel = new MDownloadDataTypeImage(response.getUser().getProfile_image().getLarge() , new IDSDownloadDataType() {
            @Override
            public void onSubscribe(MDownloadDataType mDownloadDataType) {
                Log.d("MainActivity", "onSubscribe: ");

            }

            @Override
            public void onNext(MDownloadDataType mDownloadDataType) {
                Log.d("MainActivity", "onNext: "+((MDownloadDataTypeImage)mDownloadDataType).getData());
                Holder.flightLogo.setImageBitmap(((MDownloadDataTypeImage) mDownloadDataType).getImageBitmap());

                //      imageView.setImageBitmap(((MDownloadDataTypeImage) mDownloadDataType).getImageBitmap());

            }

            @Override
            public void onError(MDownloadDataType mDownloadDataType, Throwable e) {

                Log.d("MainActivity", "onError: "+e.getMessage());
                Holder.flightLogo.setImageResource(R.drawable.ic_launcher_background);

                //         imageView.setImageResource(R.drawable.ic_launcher_background);

            }

            @Override
            public void onComplete() {
                Log.d("MainActivity", "onComplete ");

            }

        });
        mProvider.getRequest(mDataTypeImageCancel);

        Holder.txt_RideId.setText(response.getUser().getUsername()!=null?response.getUser().getUsername():"user name unknown ");



        if (!animationStates[position]) {
            animationStates[position] = true;
            Animation animation = AnimationUtils.loadAnimation(Context, R.anim.transaction_right);
            animation.setStartOffset(position*150);
            Holder.Card_DataShowActivityCard.startAnimation(animation);

            animation.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationStart(Animation animation) {}
                @Override
                public void onAnimationEnd(Animation animation) {
                    Holder.flightLogo.setVisibility(View.VISIBLE);
                    Holder.L_DataShowActivityShadow.setVisibility(View.VISIBLE);
                    Holder.l_DataShowActivityBannerContainer.setVisibility(View.VISIBLE);
           //         Holder.R_DataShowActivityDetails.setVisibility(View.VISIBLE);
                }
                public void onAnimationRepeat(Animation animation) {}
            });
        }

    }


    public int getItemCount() {
        return voucherTransfers.size();
    }
    private static class VoucherTransfersFilter extends Filter {

        private final ImageListAdapter adapter;

        private final List<Response> originalList;

        private final List<Response> filteredList;

        private VoucherTransfersFilter(ImageListAdapter adapter, List<Response> originalList) {
            super();
            this.adapter = adapter;
            this.originalList = new ArrayList<>(originalList);
            this.filteredList = new ArrayList<>();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            filteredList.clear();
            final FilterResults results = new FilterResults();

            if (constraint.length() == 0) {
                filteredList.addAll(originalList);
                Log.i("VoucherTrans_addorigin", "if perform Filtering: ");
            } else {
                final String filterPattern = constraint.toString().toLowerCase().trim();
                //  Pattern pattern = Pattern.compile(filterPattern, Pattern.CASE_INSENSITIVE);
                Log.i("VoucherTransfers_ad", "else perform Filtering: ");
                for (final Response VoucherTransfersModel : originalList) {
                    // Pattern xpattern = Pattern.compile(restaurant.getRestaurantName(), Pattern.CASE_INSENSITIVE);
                    if ((VoucherTransfersModel.getUser().getUsername()+"").toLowerCase().contains(filterPattern)) {
                        filteredList.add(VoucherTransfersModel);
                    }
                    Log.i("VoucherTransfers_added", "not perform Filtering: ");
                }
            }
            results.values = filteredList;
            results.count = filteredList.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            adapter.filteredVoucherTransfersList.clear();
            adapter.filteredVoucherTransfersList.addAll((ArrayList<Response>) results.values);
            adapter.voucherTransfers.clear();
            adapter.voucherTransfers.addAll(filteredList);
            adapter.notifyDataSetChanged();
        }
    }

}
