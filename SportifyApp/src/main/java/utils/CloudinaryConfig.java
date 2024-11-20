/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

/**
 *
 * @author carlo
 */
public class CloudinaryConfig {
    private static final String CLOUD_NAME = "dvznvnzam";
    private static final String API_KEY = "764771599468217";
    private static final String API_SECRET = "QqgeW75iLaWSnMTCSHLCoLG7YGg";

    private static Cloudinary cloudinary;

    public static Cloudinary getCloudinaryInstance() {
        if (cloudinary == null) {
            cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", CLOUD_NAME,
                "api_key", API_KEY,
                "api_secret", API_SECRET
            ));
        }
        return cloudinary;
    }
}
