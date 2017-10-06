package com.example.kisha.skct;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.example.kisha.skct.MainActivity.auth;

public class ChatTimelineActivity extends AppCompatActivity
{
    FirebaseListAdapter<Timeline> counting;
    LazyImageLoadAdapter adapter;
    FirebaseUser user;
    FirebaseDatabase db;
    DatabaseReference reference;
    StorageReference storageReference;
    ProgressDialog progressDialog;
    ArrayList<String> dispName, dispPicture, dispTimeline;
    String[] strName, strPicture, strTimeline;
    String timeUrl;
    ListView list;


    FloatingActionButton fab ,floatingActionButton;
    private static int GALLERY_INTENT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_timeline);

        fab = (FloatingActionButton) findViewById(R.id.floatingActionButton4);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton8);
        progressDialog = new ProgressDialog(ChatTimelineActivity.this);
        storageReference = FirebaseStorage.getInstance().getReference();

        dispName = new ArrayList<String>();
        dispPicture = new ArrayList<String>();
        dispTimeline = new ArrayList<String>();

        auth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        db = FirebaseDatabase.getInstance();
        reference = db.getReference();

        getStringForDisplay();



        strName = dispName.toArray(new String[dispName.size()]);
        strPicture = dispPicture.toArray(new String[dispPicture.size()]);
        strTimeline = dispTimeline.toArray(new String[dispTimeline.size()]);

        //Log.e("Name : ", strName[0]);
        //Log.e("Picture : ", dispPicture.toString());
        //Log.e("Timeline : ", dispTimeline.toString());



        final Activity a = this;

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list = (ListView) findViewById(R.id.list_of_timeline);
                adapter = new LazyImageLoadAdapter(a, strTimeline, strPicture);
                list.setAdapter(adapter);
            }
        });


        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_INTENT);
            }




        });


    }

    void getStringForDisplay()
    {
        DatabaseReference scoresRef = FirebaseDatabase.getInstance().getReference().child("Timeline");
        scoresRef.keepSynced(true);

        counting = new FirebaseListAdapter<Timeline>(this,
                Timeline.class, R.layout.timeline_layout,
                FirebaseDatabase.getInstance().getReference().child("Timeline"))
        {
            @Override
            protected void populateView(View v, Timeline model, int position)
            {
                dispPicture.add(model.getProfUrl());
                dispTimeline.add(model.getPicUrl());
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK && data != null)
        {
            progressDialog.setMessage("Posting Timeline...");
            progressDialog.setTitle("Please wait!");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCanceledOnTouchOutside(true);
            progressDialog.setCancelable(true);
            progressDialog.show();
            Uri uri = data.getData();

            StorageReference filePath = storageReference.child("timelinepics").child(Long.toString(new Date().getTime()));
            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                {
                    timeUrl = taskSnapshot.getDownloadUrl().toString();
                    reference.child("Timeline").push().setValue(new Timeline(user.getDisplayName(), timeUrl, user.getPhotoUrl().toString()));
                    progressDialog.dismiss();
                    getStringForDisplay();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), "Upload Failed!", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    Log.e("Exception : ", e.getMessage());
                }
            });
        }

    }

    class FileCache
    {

        private File cacheDir;

        public FileCache(Context context){

            //Find the dir at SDCARD to save cached images

            if (android.os.Environment.getExternalStorageState().equals(
                    android.os.Environment.MEDIA_MOUNTED))
            {
                //if SDCARD is mounted (SDCARD is present on device and mounted)
                cacheDir = new File(
                        android.os.Environment.getExternalStorageDirectory(),"SKCT_Timeline_Cache");
            }
            else
            {
                // if checking on simulator the create cache dir in your application context
                cacheDir=context.getCacheDir();
            }

            if(!cacheDir.exists()){
                // create cache dir in your application context
                cacheDir.mkdirs();
            }
        }

        public File getFile(String url){
            //Identify images by hashcode or encode by URLEncoder.encode.
            String filename=String.valueOf(url.hashCode());

            File f = new File(cacheDir, filename);
            return f;

        }

        public void clear(){
            // list all files inside cache directory
            File[] files=cacheDir.listFiles();
            if(files==null)
                return;
            //delete all cache directory files
            for(File f:files)
                f.delete();
        }

    }

    class ImageLoader
    {

        // Initialize MemoryCache
        MemoryCache memoryCache = new MemoryCache();
        FileCache fileCache;

        //Create Map (collection) to store image and image url in key value pair
        private Map<ImageView, String> imageViews = Collections.synchronizedMap(
                new WeakHashMap<ImageView, String>());
        ExecutorService executorService;

        //handler to display images in UI thread
        Handler handler = new Handler();

        public ImageLoader(Context context){

            fileCache = new FileCache(context);

            // Creates a thread pool that reuses a fixed number of
            // threads operating off a shared unbounded queue.
            executorService= Executors.newFixedThreadPool(5);

        }

        // default image show in list (Before online image download)
        final int stub_id = R.drawable.thumb;

        public void DisplayImage(String url, ImageView imageView)
        {
            //Store image and url in Map
            imageViews.put(imageView, url);

            //Check image is stored in MemoryCache Map or not (see MemoryCache.java)
            Bitmap bitmap = memoryCache.get(url);



            if(bitmap!=null){
                // if image is stored in MemoryCache Map then
                // Show image in listview row
                imageView.setImageBitmap(bitmap);
            }
            else
            {
                //queue Photo to download from url
                queuePhoto(url, imageView);

                //Before downloading image show default image
                imageView.setImageResource(stub_id);
            }
        }

        private void queuePhoto(String url, ImageView imageView)
        {
            // Store image and url in PhotoToLoad object
            PhotoToLoad p = new PhotoToLoad(url, imageView);

            // pass PhotoToLoad object to PhotosLoader runnable class
            // and submit PhotosLoader runnable to executers to run runnable
            // Submits a PhotosLoader runnable task for execution

            executorService.submit(new PhotosLoader(p));
        }

        //Task for the queue
        private class PhotoToLoad
        {
            public String url;
            public ImageView imageView;
            public PhotoToLoad(String u, ImageView i){
                url=u;
                imageView=i;
            }
        }

        class PhotosLoader implements Runnable {
            PhotoToLoad photoToLoad;

            PhotosLoader(PhotoToLoad photoToLoad){
                this.photoToLoad=photoToLoad;
            }

            @Override
            public void run() {
                try{
                    //Check if image already downloaded
                    if(imageViewReused(photoToLoad))
                        return;
                    // download image from web url
                    Bitmap bmp = getBitmap(photoToLoad.url);

                    // set image data in Memory Cache
                    memoryCache.put(photoToLoad.url, bmp);

                    if(imageViewReused(photoToLoad))
                        return;

                    // Get bitmap to display
                    BitmapDisplayer bd=new BitmapDisplayer(bmp, photoToLoad);

                    // Causes the Runnable bd (BitmapDisplayer) to be added to the message queue.
                    // The runnable will be run on the thread to which this handler is attached.
                    // BitmapDisplayer run method will call
                    handler.post(bd);

                }catch(Throwable th){
                    th.printStackTrace();
                }
            }
        }

        private Bitmap getBitmap(String url)
        {
            File f = fileCache.getFile(url);

            //from SD cache
            //CHECK : if trying to decode file which not exist in cache return null
            Bitmap b = decodeFile(f);
            if(b!=null)
                return b;

            // Download image file from web
            try {

                Bitmap bitmap=null;
                URL imageUrl = new URL(url);
                HttpURLConnection conn = (HttpURLConnection)imageUrl.openConnection();
                conn.setConnectTimeout(30000);
                conn.setReadTimeout(30000);
                conn.setInstanceFollowRedirects(true);
                InputStream is=conn.getInputStream();

                // Constructs a new FileOutputStream that writes to file
                // if file not exist then it will create file
                OutputStream os = new FileOutputStream(f);

                // See Utils class CopyStream method
                // It will each pixel from input stream and
                // write pixels to output stream (file)
                Utils.CopyStream(is, os);

                os.close();
                conn.disconnect();

                //Now file created and going to resize file with defined height
                // Decodes image and scales it to reduce memory consumption
                bitmap = decodeFile(f);

                return bitmap;

            } catch (Throwable ex){
                ex.printStackTrace();
                if(ex instanceof OutOfMemoryError)
                    memoryCache.clear();
                return null;
            }
        }

        //Decodes image and scales it to reduce memory consumption
        private Bitmap decodeFile(File f){

            try {

                //Decode image size
                BitmapFactory.Options o = new BitmapFactory.Options();
                o.inJustDecodeBounds = true;
                FileInputStream stream1=new FileInputStream(f);
                BitmapFactory.decodeStream(stream1,null,o);
                stream1.close();

                //Find the correct scale value. It should be the power of 2.

                // Set width/height of recreated image
                final int REQUIRED_SIZE=500;

                int width_tmp=o.outWidth, height_tmp=o.outHeight;
                int scale=1;
                while(true)
                {
                    if(width_tmp/2 < REQUIRED_SIZE || height_tmp/2 < REQUIRED_SIZE)
                        break;
                    width_tmp/=2;
                    height_tmp/=2;
                    scale*=2;
                }

                //decode with current scale values
                BitmapFactory.Options o2 = new BitmapFactory.Options();
                o2.inSampleSize=scale;
                FileInputStream stream2=new FileInputStream(f);
                Bitmap bitmap=BitmapFactory.decodeStream(stream2, null, o2);
                stream2.close();
                return bitmap;

            } catch (FileNotFoundException e) {
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        boolean imageViewReused(PhotoToLoad photoToLoad){

            String tag=imageViews.get(photoToLoad.imageView);
            //Check url is already exist in imageViews MAP
            if(tag==null || !tag.equals(photoToLoad.url))
                return true;
            return false;
        }

        //Used to display bitmap in the UI thread
        class BitmapDisplayer implements Runnable
        {
            Bitmap bitmap;
            PhotoToLoad photoToLoad;
            public BitmapDisplayer(Bitmap b, PhotoToLoad p){bitmap=b;photoToLoad=p;}
            public void run()
            {
                if(imageViewReused(photoToLoad))
                    return;

                // Show bitmap on UI
                if(bitmap!=null)
                    photoToLoad.imageView.setImageBitmap(bitmap);
                else
                    photoToLoad.imageView.setImageResource(stub_id);
            }
        }

        public void clearCache() {
            //Clear cache directory downloaded images and stored data in maps
            memoryCache.clear();
            fileCache.clear();
        }

    }

    class LazyImageLoadAdapter extends BaseAdapter implements View.OnClickListener
    {

        private Activity activity;
        private String[] data ,data1;
        private LayoutInflater inflater=null;
        public ImageLoader imageLoader;

        public LazyImageLoadAdapter(Activity a, String[] d, String[] dd)
        {
            activity = a;
            data=d;
            data1 = dd;

            inflater = (LayoutInflater)activity.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            // Create ImageLoader object to download and show image in list
            // Call ImageLoader constructor to initialize FileCache
            imageLoader = new ImageLoader(activity.getApplicationContext());
        }

        public int getCount() {
            return data.length;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        /********* Create a holder Class to contain inflated xml file elements *********/
        public class ViewHolder{

            public ImageView image, imageProfile;
            public TextView textView;

        }

        public View getView(int position, View convertView, ViewGroup parent) {

            View vi=convertView;
            ViewHolder holder;

            if(convertView==null){

                /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
                vi = inflater.inflate(R.layout.timeline_layout, null);

                /****** View Holder Object to contain tabitem.xml file elements ******/

                holder = new ViewHolder();
                holder.image=(ImageView)vi.findViewById(R.id.imageView64);
                holder.textView = (TextView) findViewById(R.id.textView127);
                holder.imageProfile = (ImageView) findViewById(R.id.imageView51);

                /************  Set holder with LayoutInflater ************/
                vi.setTag( holder );
            }
            else
                holder=(ViewHolder)vi.getTag();

            ImageView image = holder.image;
            ImageView profile = holder.imageProfile;

            //DisplayImage function from ImageLoader Class
            imageLoader.DisplayImage(data[position], image);
            imageLoader.DisplayImage(data1[position], profile);
            holder.textView.setText(strName[position]);

            /******** Set Item Click Listner for LayoutInflater for each row ***********/
            vi.setOnClickListener(new OnItemClickListener(position));
            return vi;
        }

        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub

        }


        /********* Called when Item click in ListView ************/
        private class OnItemClickListener  implements View.OnClickListener {
            private int mPosition;

            OnItemClickListener(int position){
                mPosition = position;
            }

            @Override
            public void onClick(View arg0) {
                ChatTimelineActivity sct = (ChatTimelineActivity) activity;
                sct.onItemClick(mPosition);
            }
        }
    }

    class MemoryCache
    {

        private static final String TAG = "MemoryCache";

        //Last argument true for LRU ordering
        private Map<String, Bitmap> cache = Collections.synchronizedMap(
                new LinkedHashMap<String, Bitmap>(10,1.5f,true));

        //current allocated size
        private long size=0;

        //max memory cache folder used to download images in bytes
        private long limit=1000000;

        MemoryCache(){

            //use 25% of available heap size
            setLimit(Runtime.getRuntime().maxMemory()/4);
        }

        public void setLimit(long new_limit){

            limit=new_limit;
            Log.i(TAG, "MemoryCache will use up to "+limit/1024./1024.+"MB");
        }

        public Bitmap get(String id){
            try{
                if(!cache.containsKey(id))
                    return null;

                return cache.get(id);

            }catch(NullPointerException ex){
                ex.printStackTrace();
                return null;
            }
        }

        public void put(String id, Bitmap bitmap){
            try{
                if(cache.containsKey(id))
                    size-=getSizeInBytes(cache.get(id));
                cache.put(id, bitmap);
                size+=getSizeInBytes(bitmap);
                checkSize();
            }catch(Throwable th){
                th.printStackTrace();
            }
        }

        private void checkSize() {
            Log.i(TAG, "cache size="+size+" length="+cache.size());
            if(size>limit){

                //least recently accessed item will be the first one iterated
                Iterator<Map.Entry<String, Bitmap>> iter=cache.entrySet().iterator();

                while(iter.hasNext()){
                    Map.Entry<String, Bitmap> entry=iter.next();
                    size-=getSizeInBytes(entry.getValue());
                    iter.remove();
                    if(size<=limit)
                        break;
                }
                Log.i(TAG, "Clean cache. New size "+cache.size());
            }
        }

        public void clear() {
            try{
                // Clear cache
                cache.clear();
                size=0;
            }catch(NullPointerException ex){
                ex.printStackTrace();
            }
        }

        long getSizeInBytes(Bitmap bitmap) {
            if(bitmap==null)
                return 0;
            return bitmap.getRowBytes() * bitmap.getHeight();
        }
    }

    static class Utils
    {
        static void CopyStream(InputStream is, OutputStream os)
        {
            final int buffer_size=1024;
            try
            {

                byte[] bytes=new byte[buffer_size];
                for(;;)
                {
                    //Read byte from input stream

                    int count=is.read(bytes, 0, buffer_size);
                    if(count==-1)
                        break;

                    //Write byte from output stream
                    os.write(bytes, 0, count);
                }
            }
            catch(Exception ex){ex.printStackTrace();}
        }
    }

    @Override
    public void onDestroy()
    {
        // Remove adapter refference from list
        list.setAdapter(null);
        super.onDestroy();
    }

    public View.OnClickListener listener = new View.OnClickListener()
    {
        @Override
        public void onClick(View arg0) {

            //Refresh cache directory downloaded images
            adapter.imageLoader.clearCache();
            adapter.notifyDataSetChanged();
        }
    };

    public void onItemClick(int mPosition)
    {
        String tempValues = strTimeline[mPosition];

        Toast.makeText(getApplicationContext(), "Image URL : "+tempValues, Toast.LENGTH_LONG).show();
    }



}
