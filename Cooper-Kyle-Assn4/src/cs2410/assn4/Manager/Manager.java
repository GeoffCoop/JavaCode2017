package cs2410.assn4.Manager;
import javafx.scene.image.Image;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by User on 10/5/2016.
 */
public class Manager {
    public ArrayList<String> imageList = new ArrayList<String>();
    public int iterator = 0;

    /**
     * to manage the different functions and what they need to do.
     */
    public Manager(){
        try
        {
            Scanner scan = new Scanner(new FileReader("data/images.data"));
            while (scan.hasNext())
            {
                imageList.add(scan.next());
            }
        }
        catch(FileNotFoundException e){}

    }

    /**
     * get next image, increment iterator
     * @return next image
     */
    public Image nextImage()
    {
        if(iterator >= imageList.size()-1)
        {iterator = 0;}
        else iterator++;
        return new Image(imageList.get(iterator),400,0,true,false);
    }

    /**
     * get previous image, decrement iterator
     * @return previous image
     */
    public Image prevImage()
    {
        if(iterator == 0) iterator = imageList.size() -1;
        else iterator--;
        return new Image (imageList.get(iterator),400,0,true,false);
    }

    /**
     * add an image
     * @param url string
     * @return Image from url
     */
    public Image addImage(String url)
    {
        imageList.add(iterator, url);
        return new Image (imageList.get(iterator),400,0,true,false);
    }

    /**
     * delete image from imageList
     * @param url of the image to delete
     */
    public void delImage(String url)
    {
        imageList.remove(imageList.indexOf(url));
    }

    //exit
    public void quit()
    {
        System.exit(0);
    }
}
