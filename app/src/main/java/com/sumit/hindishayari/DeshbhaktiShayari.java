package com.sumit.hindishayari;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.navigation.NavigationView;

public class DeshbhaktiShayari extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;

    TextView shayaricount;

    ViewPager viewpager;
    MyAdapter ma;

    ImageButton previous;
    ImageButton next;
    ImageButton whatsapp;
    ImageButton copy;
    private AdView mAdView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deshbhakti_shayari);


        //for navigationnnnn
        setUpToolbar();
        navigationView =  findViewById(R.id.nav1);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        Intent home= new Intent(getBaseContext(),MainActivity.class);
                        startActivity(home);
                        break;
                    case R.id.nav_dev:
                        Intent dev= new Intent(getBaseContext(),DeveloperSumit.class);
                        startActivity(dev);
                        break;
                    case R.id.nav_share:
                        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        String shareBody = "Hi I have downloaded Hindi Shayari app. \nYou should try this \n" +
                                " https://play.google.com/store/apps/details?id=com.sumit.hindishayari";
                        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Hindi Shayari");
                        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                        startActivity(Intent.createChooser(sharingIntent, "Share via"));
                        break;
                    case R.id.nav_rate:
                        try {
                            Uri uri = Uri.parse("market://details?id="+"com.sumit.hindishayari");
                            Intent goMarket = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(goMarket);
                        }catch (ActivityNotFoundException e){
                            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.sumit.hindishayari");
                            Intent goMarket = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(goMarket);
                        }
                        break;
                    case R.id.nav_exit:
                        finishAffinity();
                        System.exit(0);
                        break;
                }
                return false;
            }
        });


        //for viewpager
        viewpager= (ViewPager) findViewById(R.id.vp);
        final String[] Deshbhaktimsg =
                {
                        "चूमा था वीरों ने फांसी का फंदा\n" +
                                "यूँ ही नहीं मिली थी आजादी खैरात में",

                        "वतन की सर बुलंदी में, हमारा नाम हो शामिल,\n" +
                                "गुजरते रहना है हमको, सदा ऐसे मुकामो से।",


                        "जब आँख खुले तो धरती हिन्दुस्तान की हो:\n" +
                                "जब आँख बंद हो तो यादेँ हिन्दुस्तान की हो:\n" +
                                "हम मर भी जाए तो कोई गम नही लेकिन,\n" +
                                "मरते वक्त मिट्टी हिन्दुस्तान की हो।",

                        "देश की हिफाजत मरते दम तक करेंगे\n" +
                                "दुश्मन की हर गोली का हम सामना करेंगे\n" +
                                "आजाद हैं और आजाद ही रहेंगें\n" +
                                "जय हिन्द !!",

                        "लड़े जंग वीरों की तरह,\n" +
                                "जब खून खौल फौलाद हुआ |\n" +
                                "मरते दम तक डटे रहे वो,\n" +
                                "तब ही तो देश आजाद हुआ ||",

                        "ये दुनिया….एक दुल्हन\n" +
                                "ये दुनिया….एक दुल्हन…दुल्हन के माथे पे बिंदिया\n" +
                                "I Love My India",
                        " लहराएगा तिरंगा अब सारे आसमान पर\n" +
                                "भारत का ही नाम होगा सबकी जुबान पर\n" +
                                "ले लेंगे उसकी जान या खेलेंगे अपनी जान पर\n" +
                                "कोई जो उठाएगा आँख हिंदुस्तान पर",

                        "गुलाम बने इस देश को आजाद तुमने कराया है\n" +
                                "सुरक्षित जीवन देकर तुमने कर्ज अपना चुकाया है\n" +
                                "दिल से तुमको नमन हैं करते\n" +
                                "ये आजाद वतन जो दिलाया है",

                        "इस वतन के रखवाले हैं हम\n" +
                                "शेर ए जिगर वाले हैं हम\n" +
                                "मौत से हम नहीं डरते\n" +
                                "मौत को बाँहों में पाले हैं हम\n" +
                                "वन्दे मातरम…",

                        " जब देश में थी दिवाली, वो झेल रहे थे गोली\n" +
                                "जब हम बैठे थे घरों में, वो खेल रहे थे होली\n" +
                                "क्या लोग थे वो अभिमानी\n" +
                                "है धन्य वो उनकी जवानी",

                        " खींच दो अपने ख़ूँ से जमीं पर लकीर\n" +
                                "इस तरफ आने पाये ना रावण कोई\n" +
                                "तोड़ दो अगर कोई हाथ उठने लगे\n" +
                                "छू ना पाये सीता का दामन कोई\n" +
                                "राम भी तुम तुम्हीं लक्ष्मण साथियो\n" +
                                "अब तुम्हारे हवाले वतन साथियो",

                        "जब आँख खुले तो धरती हिन्दुस्तान की हो:\n" +
                                "जब आँख बंद हो तो यादेँ हिन्दुस्तान की हो:\n" +
                                "हम मर भी जाए तो कोई गम नही लेकिन,\n" +
                                "मरते वक्त मिट्टी हिन्दुस्तान की हो।",



                        "तेरे दामन से जो आये, उन हवाओं को सलाम\n" +
                                "चूम लूँ मैं उस जुबां को जिस पे आये तेरा नाम\n" +
                                "सबसे सुन्दर सुबह तेरी\n" +
                                "सबसे सुन्दर तेरी शाम\n" +
                                "तुझ पे दिल कुरबान\n" +
                                "ऐ मेरे प्यारे वतन,\n" +
                                "ऐ मेरे पिछड़े चमन\n" +
                                "तुझ पे दिल कुर्बान।।",

                        " तिरंगा है आन मेरी\n" +
                                "तिरंगा ही है शान मेरी\n" +
                                "तिरंगा रहे सदा ऊँचा हमारा\n" +
                                "तिरंगे से है धरती महान मेरी",


                        "आजादी की कभी शाम नहीं होने देंगे,\n" +
                                "शहीदों की कुर्बानी बदनाम नहीं होने देंगे,\n" +
                                "बची हो जो एक बूँद भी लहू की तब तक,\n" +
                                "भारत माता का आँचल नीलाम नहीं होने देंगे।",

                        "लहराएगा तिरंगा अब नीले आसमान पर,\n" +
                                "भारत का नाम होगा सबकी जुबान पर,\n" +
                                "ले लेंगे उसकी जान या दे देंगे अपनी जान,\n" +
                                "कोई जो उठाएगा आँख हमारे हिन्दुस्तान पर।",



                        "खुशनसीव हैं वो जो\n" +
                                "वतन पे मिट जाते हैं,\n" +
                                "मर कर भी वो लोग\n" +
                                "अमर हो जाते हैं,\n" +
                                "करता हूँ तुम्हे सलाम\n" +
                                "ऐ वतन पर मिटने वालो,\n" +
                                "तुम्हारी हर सांस में बसना\n" +
                                "तिरंगे का नसीव है।\n" +
                                "जय हिन्द...!",

                        "वतन की मोहब्बत, दिल में दबाये बैठे है,\n" +
                                "मरेगे वतन के लिए\n" +
                                "शर्त, शहादत से लगाये बैठे हैं! ??",

                        "न केशरिया मेरा है न हरा मेरा है\n" +
                                "मेरा धर्म हिन्दुस्तानी है\n" +
                                "पूरा तिरंगा मेरा है",

                        "हर मजहब से सिखा हमने पहले\n" +
                                "देश का नारा\n" +
                                "मत बाटो इसे एक ही रहने दो\n" +
                                "प्यारा हिंदुस्तान हमारा",

                        "तहे दिल से सलाम हैं भारत के ऐसे\n" +
                                "माई के लाल को\n" +
                                "जिन्होंने अपनी मात्रभूमि के लिए\n" +
                                "हस्ते हस्ते जान बलिदान कर दिया\n" +
                                "भारत माता की जय",

                        "मेरी जान तु सदा जिंदाबाद रहे तू\n" +
                                "ऐ मेरे प्यारे वतनआबाद रहे तू",

                        "शहीद हो गए जो ओढ़ तिरंगा\n" +
                                "भारत मा की गोद में\n" +
                                "फिर होंगे ये वीर पैदा\n" +
                                "किसी भारत माँ की गोद से",

                        "दिलो से नफरतो को निकालो,\n" +
                                "देश के इन गद्दारों को मारो,\n" +
                                "ये देश है खतरे में ए -मेरे -देशवालो,\n" +
                                "हिन्दुस्तान के सम्मान को बचा लो!!",

                        "मेरे वतन का राष्ट्रगान बंगा से है\n" +
                                "हमारे वतन की शान गंगा से है\n" +
                                "जो वीर मर मिटे देश की मिटटी पर\n" +
                                "उन शहीदों का अभिमान तिरंगा से है",

                        "हमें मालूम है\n" +
                                "की इस रास्ते पर कदम कदम पर मौत है\n" +
                                "फिर भी इस वतन की खिदमत का जूनून है\n" +
                                "यह तुम्हारे इर्द गिर्द इसलिए इतना सुकून है\n" +
                                "क्योकि इस हवा में हम जवानों का खून है",

                        "न आरजू जन्नत की न ही मौत की फिक्र\n" +
                                "चाहती है जिंदगी बस शहीदों में हो जिक्र",

                        "गरज उठे गगन सारा\n" +
                                "समुन्दर छोड़े अपना किनारा\n" +
                                "हिल जाये जहाँ सारा जब गूंजे\n" +
                                "इंकलाब का नारा\n" +
                                "भारत माता की जय",

                        "कुछ नशा ये तिरंगे की आन का है\n" +
                                "कुछ नशा मातृभूमि के मान का है\n" +
                                "हम लहराएंगे  हर जगह ये तिरंगा\n" +
                                "नशा ये हिंदुस्तान की शान का है\n" +
                                "नशा ये हिंदुस्तान की शान का है",


                        "जो धर्म पे मर मिटा बस वही महान है\n" +
                                "कारगिल का हर जवान देवता समान है\n" +
                                "कारगिल का हर जवान देवता समान",

                        "मुझे ना तन चाहिए ना धन चाहिए\n" +
                                "बस अमन से भरा ये वतन चाहिए\n" +
                                "जब तक जिन्दा रहूं इस मातृभूमि के लिए\n" +
                                "और जब मरूं तो तिरंगा कफन चाहिए.\n" +
                                "जब मरूं तो तिरंगा कफन चाहिए",

                        "देश के रखवाले है हम,\n" +
                                "शेर-ए-जिगर वाले है हम,\n" +
                                "शहादत से हमें क्यों डर लगेगा,\n" +
                                "मौत के बांहों में पाले हुए है हम.",

                        "नहीं भूल सकते किसी भी बलिदान को\n" +
                                "हर शहीद का बदला लिया जायेगा",

                        "देशभक्तों से ही देश की शान है\n" +
                                "देशभक्तों से ही देश का मान है\n" +
                                "हम उस देश के फूल है यारो\n" +
                                "जिस देश का नाम हिन्दुस्तान है",

                        "दिन हमारे एक हैं एक ही है हमारी जान\n" +
                                "हिन्दुस्तान हमारा है हम हैं इसकी शान\n" +
                                "जान लुटा देंगे वतन पे हो जाएंगे कुर्बान\n" +
                                "इसीलिए हम कहते हैं मेरा भारत महान",

                        "न दे दौलत न देश शोहरत.\n" +
                                "कोई शिकवा नहीं हमको\n" +
                                "झुका दूं सर में दुश्मन का\n" +
                                "ये ही हिम्मत का धन देना अगर देना\n" +
                                "तो दिल में देशभक्ति का चलन देना",

                        "तिरंगा है आन मेरी\n" +
                                "तिरंगा ही है शान मेरी\n" +
                                "तिरंगा रहे सदा ऊँचा हमारा\n" +
                                "तिरंगे से है धरती महान मेरी जय हिन्द",

                        "तैरना है तो समंदर में तेरो|\n" +
                                "नदी नालों में क्या रखा है\n" +
                                "प्यार करना है तो वतन से करो\n" +
                                "इन बेवफा लोगों में क्या रखा है",

                        "मेरा यही अंदाज ज़माने को खलता है,\n" +
                                "कि चिराग हवा के खिलाफ क्यों जलता है,\n" +
                                "मैं अमन पसंद हूँ,\n" +
                                "मेरे शहर में दंगा रहने दो,\n" +
                                "लाल और हरे में मत बांटो,\n" +
                                "मेरी छत पर तिरंगा रहने दो।",

                        "कुछ हाथ से मेरे निकल गया,\n" +
                                "वो पलक झपक के छिप गया,\n" +
                                "फिर लाश बिछ गयी लाखों की,\n" +
                                "सब पलक झपक के बदल गया।\n" +
                                "जब रिश्ते राख में बदल गए,\n" +
                                "इंसानियत का दिल दहल गया,\n" +
                                "मैं पूछ पूछ के हार गया,\n" +
                                "क्यूँ मेरा भारत बदल गया?",

                        "बड़े अनमोल हे ये खून के रिश्ते\n" +
                                "इनको तू बेकार न कर,\n" +
                                "मेरा हिस्सा भी तू ले ले मेरे भाई\n" +
                                "घर के आँगन में दीवार ना कर।",

                        "दोस्ताना इतना बरकरार रखो कि,\n" +
                                "मजहब बीच में न आये कभी,\n" +
                                "तुम उसे मंदिर तक छोड़ दो ,\n" +
                                "वो तुम्हें मस्जिद छोड़ आये कभी।",

                        "आज मुझे फिर इस बात का गुमान हो,\n" +
                                "मस्जिद में भजन मंदिरों में अज़ान हो,\n" +
                                "खून का रंग फिर एक जैसा हो,\n" +
                                "तुम मनाओ दिवाली मेरे घर रमजान हो।",

                        "मैं मुस्लिम हूँ, तू हिन्दू है, हैं दोनों इंसान,\n" +
                                "ला मैं तेरी गीता पढ़ लूँ, तू पढ ले कुरान,\n" +
                                "अपने तो दिल में है दोस्त, बस एक ही अरमान,\n" +
                                "एक थाली में खाना खाये सारा हिन्दुस्तान।",

                        "दिल हमारे एक हैं एक ही है हमारी जान,\n" +
                                "हिंदुस्तान हमारा है हम हैं इसकी शान,\n" +
                                "जान लुटा देंगे वतन पे हो जायेंगे कुर्बान,\n" +
                                "इसलिए हम कहते हैं मेरा भारत महान।",

                        "बस ये बात हवाओं को बताये रखना,\n" +
                                "रौशनी होगी चिरागों को जलाये रखना,\n" +
                                "लहू देकर जिसकी हिफाज़त की शहीदों ने,\n" +
                                "उस तिरंगे को सदा दिल में बसाये रखना।",


                        "उनके हौसले का भुगतान क्या करेगा कोई,\n" +
                                "उनकी शहादत का क़र्ज़ देश पर उधार है,\n" +
                                "आप और हम इस लिए खुशहाल हैं क्योंकि,\n" +
                                "सीमा पे सैनिक शहादत को तैयार हैं।",

                        "मुझे तन चाहिए न धन चाहिए,\n" +
                                "बस अमन से भरा ये वतन चाहिए,\n" +
                                "जब तक जिंदा रहूँ इस मात्रभूमि के लिए,\n" +
                                "और जब मरू तो तिरंगा कफ़न चाहिए।",

                        "इश्क तो करता है हर कोई,\n" +
                                "महबूब पर मरता है हर कोई,\n" +
                                "कभी वतन को महबूब बना कर देखो,\n" +
                                "फिर तुझ पर मरेगा हर कोई।",




                        "दिलों की नफरत को निकालो,\n" +
                                "वतन के इन दुश्मनों को मारो,\n" +
                                "ये देश है खतरे में ए -मेरे -हमवतन,\n" +
                                "भारत माँ के सम्मान को बचा लो!!",

                        "मैं मुल्क की हिफाजत करूँगा\n" +
                                "ये मुल्क मेरी जान है\n" +
                                "इसकी रक्षा के लिए\n" +
                                "मेरा दिल और जां कुर्बान है",

                        "काले गोरे का भेद नहीं,\n" +
                                "हर दिल से हमारा नाता है,\n" +
                                "कुछ और न आता हो हमको,\n" +
                                "हमें प्यार निभाना आता है।",

                        "गूंजे कहीं पर शंख , कहीं पे अजान है,\n" +
                                "बाइबिल है, ग्रन्थ सहाब है,गीता का ज्ञान है,\n" +
                                "दुनिया में कहीं और ये मंजर नसीब नहीं,\n" +
                                "दिखा दो  दुनिया को के ये हिन्दुस्तान है।",

                        "ऐ मेरे वतन के लोगो\n" +
                                "तुम खूब लगा लो नारा,\n" +
                                "ये शुभ दिन है हम सब का\n" +
                                "लहराओ तिरंगा प्यारा,\n" +
                                "पर मत भूलो सीमा पर\n" +
                                "वीरों ने प्राण गवाए,\n" +
                                "कुछ याद उन्हें भी कर लो\n" +
                                "जो लौट के घर ना आये।",



                        "मैं मुल्क की हिफाजत करूँगा\n" +
                                "ये मुल्क मेरी जान है\n" +
                                "इसकी रक्षा के लिए\n" +
                                "मेरा दिल और जां कुर्बान है",

                        "अनेकता में एकता ही इस देश की शान है,\n" +
                                "इसीलिए मेरा भारत महान है",

                        "मैं भारतवर्ष का हरदम अमिट सम्मान करता हूँ\n" +
                                "यहाँ की चांदनी मिट्टी का ही गुणगान करता हूँ,\n" +
                                "मुझे चिंता नहीं है स्वर्ग जाकर मोक्ष पाने की,\n" +
                                "तिरंगा हो कफ़न मेरा, बस यही अरमान रखता हूँ।",

                        "जो देश के लिए शहीद हुए\n" +
                                "उनको मेरा सलाम है\n" +
                                "अपने खूं से जिस जमीं को सींचा\n" +
                                "उन बहादुरों को सलाम है..",

                        "खुशनसीब हैं वो जो वतन पर मिट जाते हैं,\n" +
                                "मरकर भी वो लोग अमर हो जाते हैं,\n" +
                                "करता हूँ उन्हें सलाम ए वतन पे मिटने वालों,\n" +
                                "तुम्हारी हर साँस में तिरंगे का नसीब बसता है…",

                        "लिख रहा हूं मैं अजांम जिसका कल आगाज आयेगा,\n" +
                                "मेरे लहू का हर एक कतरा इकंलाब लाऐगा\n" +
                                "मैं रहूँ या ना रहूँ पर ये वादा है तुमसे मेरा कि,\n" +
                                "मेरे बाद वतन पर मरने वालों का सैलाब आयेगा",

                        "मुझे ना तन चाहिए, ना धन चाहिए\n" +
                                "बस अमन से भरा यह वतन चाहिए\n" +
                                "जब तक जिन्दा रहूं, इस मातृ-भूमि के लिए\n" +
                                "और जब मरुँ तो तिरंगा कफ़न चाहिये\n" +
                                "* जय-हिन्द *",

                        "इतनी सी बात हवाओं को बताये रखना\n" +
                                "रौशनी होगी चिरागों को जलाये रखना\n" +
                                "लहू देकर की है जिसकी हिफाजत हमने\n" +
                                "ऐसे तिरंगे को हमेशा दिल में बसाये रखना",

                        " देशभक्तों से ही देश की शान है\n" +
                                "देशभक्तों से ही देश का मान है\n" +
                                "हम उस देश के फूल हैं यारों\n" +
                                "जिस देश का नाम हिंदुस्तान है",

                        "मेरे देश तुझको नमन है मेरा,\n" +
                                "जीऊं तो जुबां पर नाम हो तेरा\n" +
                                "मरूं तो तिरंगा कफन हो मेरा",


                        " जिंदगी है कल्पनाओं की जंग\n" +
                                "कुछ तो करो इसके लिए दबंग\n" +
                                "जियो शान से भरो उमंग\n" +
                                "लहराओ सबसे दिलों में देश के लिए तिरंग",
                        "अधिकार मिलते नहीं लिए जाते हैं\n" +
                                "आजाद हैं मगर गुलामी किये जाते हैं\n" +
                                "वंदन करो उन सेनानियों को\n" +
                                "जो मौत के आँचल में जिए जाते हैं",

                        " उड़ जाती है नींद ये सोचकर\n" +
                                "कि सरहद पे दी गयीं वो कुर्बानियां\n" +
                                "मेरी नींद के लिए थीं",

                        "इश्क तो करता है हर कोई\n" +
                                "महबूब पे तो मरता है हर कोई\n" +
                                "कभी वतन को महबूब बना के देखो\n" +
                                "तुझ पे मरेगा हर कोई",

                        "कुछ नशा तिरंगे की आन का है,\n" +
                                "कुछ नशा मातृभूमि की मान का है,\n" +
                                "हम लहरायेंगे हर जगह ये तिरंगा,\n" +
                                "नशा ये हिन्दुस्तान की शान का है….",

                        "उनके हौंसले का मुकाबला ही नहीं है कोई\n" +
                                "जिनकी कुर्बानी का कर्ज हम पर उधार है\n" +
                                "आज हम इसीलिए खुशहाल हैं क्यूंकि\n" +
                                "सीमा पे जवान बलिदान को तैयार है….",



                        "वतन की मोहब्बत में खुद को तपाये बैठे है,\n" +
                                "मरेगे वतन के लिए शर्त मौत से लगाये बैठे हैं! \uD83C\uDDEE\uD83C\uDDF3",

                        "लुटेरा है अगर आजाद तो अपमान सबका है,\n" +
                                "लुटी है एक बेटी तो लुटा सम्मान सबका है,\n" +
                                "बनो इंसान पहले छोड़ कर तुम बात मजहब की,\n" +
                                "लड़ो मिलकर दरिंदों से ये हिंदुस्तान सबका है। \uD83C\uDDEE\uD83C\uDDF3",

                        "कुछ नशा तिरंगे की आन का है,\n" +
                                "कुछ नशा मातृभूमि की शान का है,\n" +
                                "हम लहराएंगे हर जगह..\n" +
                                "ये तिरंगा नशा ये हिंदुस्तान की शान का है।\n" +
                                "Happy Independence Day \uD83C\uDDEE\uD83C\uDDF3",

                        "ना सरकार मेरी है ना रौब मेरा है,\n" +
                                "ना बड़ा सा नाम मेरा है,\n" +
                                "मुझे तो एक छोटी सी बात का गौरव है,\n" +
                                "मै हिन्दुस्तान का हूँ और हिन्दुस्तान मेरा है,\n" +
                                "जय हिन्द \uD83C\uDDEE\uD83C\uDDF3",

                        "खुशनसीव हैं वो जो\n" +
                                "वतन पे मिट जाते हैं,\n" +
                                "मर कर भी वो लोग\n" +
                                "अमर हो जाते हैं,\n" +
                                "करता हूँ तुम्हे सलाम\n" +
                                "ऐ वतन पर मिटने वालो,\n" +
                                "तुम्हारी हर सांस में बसना\n" +
                                "तिरंगे का नसीव है।\n" +
                                "जय हिन्द…!",

                        "गूंज रहा है दुनिया में भारत का नगाड़ा,\n" +
                                "चमक रहा आसमान में देश का सितारा,\n" +
                                "आजादी के दिन आओ मिलकर करें दुआ,\n" +
                                "की बुलंदी पर लहराता रहे तिरंगा हमारा।\n",

                        "शहीदों की चिताओं पर लगेंगे हर बरस मेले,\n" +
                                "वतन पे मर मिटनेवालों का बाकी यही निशां होगा",

                        "जो देश के लिए शहीद हुए\n" +
                                "उनको मेरा सलाम है\n" +
                                "अपने खूं से जिस जमीं को सींचा\n" +
                                "उन बहादुरों को सलाम है..",

                        "मुझे ना तन चाहिए, ना धन चाहिए\n" +
                                "\n" +
                                "बस अमन से भरा यह वतन चाहिए\n" +
                                "\n" +
                                "जब तक जिन्दा रहूं, इस मातृ-भूमि के लिए\n" +
                                "\n" +
                                "और जब मरुँ तो तिरंगा कफ़न चाहिये",

                        "ऐ मेरे वतन के लोगों तुम खूब लगा लो नारा\n" +
                                "\n" +
                                "ये शुभ दिन है हम सब का लहरा लो तिरंगा प्यारा\n" +
                                "\n" +
                                "पर मत भूलो सीमा पर वीरों ने है प्राण गँवाए\n" +
                                "\n" +
                                "कुछ याद उन्हें भी कर लो जो लौट के घर न आये",

                        "खून से खेलेंगे होली,\n" +
                                "\n" +
                                "अगर वतन मुश्किल में है\n" +
                                "\n" +
                                "सरफ़रोशी की तमन्ना\n" +
                                "\n" +
                                "अब हमारे दिल में है,",


                         "अपनी आजादी को हम हरगिज मिटा सकते नही !\n" +
                                 "सर कटा सकते हैं लेकिन सर झुका सकते नही!!",

                        "किसी गजरे की खुशबु को महकता छोड़ आया हूँ,\n" +
                                "मेरी नन्ही सी चिड़िया को चहकता छोड़ आया हूँ,\n" +
                                "मुझे छाती से अपनी तू लगा लेना ऐ भारत माँ,\n" +
                                "मैं अपनी माँ की बाहों को तरसता छोड़ आया हूँ।",

                        "चैन ओ अमन का देश है मेरा, इस देश में दंगा रहने दो!\n" +
                                "लाल हरे में मत बांटो, इसे शान ए तिरंगा रहने दो",

                        "इतनी सी बात हवाओं को बताए रखना,\n" +
                                "रोशनी होगी चिरागों को जलाए रखना,\n" +
                                "लहू देकर की है जिसकी हिफाजत हमने,\n" +
                                "ऐसे तिरंगे को दिल में हमेशा बसाए रखना।",

                        "मैं भारत देश का हरदम अमित सम्मान करता हूं,\n" +
                                "यहां की चांदनी मिट्टी का गुणगान करता हूं,\n" +
                                "मुझे चिंता नहीं है स्वर्ग जाकर मोक्ष पाने की,\n" +
                                "तिरंगा हो कफ़न मेरा बस यही अरमान रखता हूं।",

                        "ना सरकार मेरी है ना रौब मेरा है,\n" +
                                "ना बड़ा सा नाम मेरा है,\n" +
                                "मुझे तो एक छोटी सी बात का गौरव है,\n" +
                                "मै हिन्दुस्तान का हूँ और हिन्दुस्तान मेरा है",

                        "गूंज रहा है दुनिया में भारत का नगाड़ा,\n" +
                                "चमक रहा आसमान में देश का सितारा,\n" +
                                "आजादी के दिन आओ मिलकर करें दुआ,\n" +
                                "की बुलंदी पर लहराता रहे तिरंगा हमारा।",

                        "फ़िदा-ए-मुल्क होना हासिल-ए-क़िस्मत समझते हैं\n" +
                                "\n" +
                                "वतन पर जान देने ही को हम जन्नत समझते हैं",
                        "किन मंज़िलों लुटे हैं मोहब्बत के क़ाफ़िले\n" +
                                "\n" +
                                "इंसाँ ज़मीं पे आज ग़रीब-उल-वतन सा है",

                        "याद अपने वतन की मुझे आती नहीं अब तो\n" +
                                "अब भूल चुका होगा मुझे मेरा वतन भी",

                        "शाम-ए-वतन कुछ अपने शहीदों का ज़िक्र कर\n" +
                                "\n" +
                                "जिन के लहू से सुब्ह का चेहरा निखर गया\n" +
                                "\n" +
                                "  \n" +
                                "\n" +
                                "बहुत अज़ीज़ है अपने वतन की ख़ाक हमें\n" +
                                "\n" +
                                "जो ख़्वाब आँखों में आया वो मोतबर आया",

                        "मेरी हर एक सांस # तिरंगे के नाम है\n" +
                                "# मेरे वतन का # हर एक नागरिक\n" +
                                "# भारत का स्वाभिमान है # वन्दे मातरम्\n",

                        "आज नशा # तिरंगे की आन का है\n" +
                                "# थोड़ा सा # हिंदुस्तान की मिट्टी का \n" +
                                "भारत में लहरेगा तिरंगा # यह \n" +
                                "# हिंदुस्तान का पैगाम है # जय भारत मां",


                        "तिरंगा मेरी शान है # हिंदुस्तान की आन है\n" +
                                "# भारत की आजादी के लिए शहीद होने\n" +
                                "वाला # सचा हिन्दुस्तानी है # indian है",

                        "चलो फिर से खुद को जगाते है\n" +
                                "\n" +
                                "अनुशासन का डंडा फिर से घुमाते है,\n" +
                                "सुहाना रंग है वीर तिरंगे का,\n" +
                                "शहीदों के लहू से,\n" +
                                "चलो शहीदों के लिए सिर झुकाते है",

                        "भारत का वीर जवान हूं \n" +
                                "\n" +
                                "ना हिन्दू हूं ना मुस्लिम हूं \n" +
                                "झखमो से भरा सिना है\n" +
                                "पर दुश्मनों के लिए चट्टान हूं में,\n" +
                                "क्योंकि भारत का वीर जवान हूं में",

                        "उनके हौसले का भुगतान क्या करेगा कोई\n" +
                                "\n" +
                                "उनकी शहादत का कर्ज देश पर उधार है\n" +
                                "आप और हम इसलिए खुशहाल है\n" +
                                "क्योंकि सीमा पर जवान सहादत के लिए तैयार है",

                        "दे सलामी इस तिरंगे को \n" +
                                "\n" +
                                "जिसमे तेरी शान है,\n" +
                                "सर हमेशा ऊंचा रखना इसका\n" +
                                "जब तक सीने में जान है",

                        "आजादी की कभी शाम नहीं होने देंगे \n" +
                                "शहीदों की कुर्बानी व्यर्थ नहीं होने देंगे\n" +
                                "बची हो जब एक बूंद गर्म लहू की\n" +
                                "तब तक भारत की आजादी पर आंच न\n" +
                                "आने देंगे",

                        "वो तिरंगे वाली DP हो\u200C तो लगा \n" +
                                "लेना भाई जी सुना है कल देशभक्ति \n" +
                                "दिखने वाली तारीख है!!\n",

                        "अलग है धर्म, जात , भेष , परिवेश\n" +
                                "\n" +
                                "पर सबका एक है गौरव तिरंगा सर्वश्रेष्ठ !!",

                        "फिर उड़ गई नींद मेरी यह सोचकर \n" +
                                "\n" +
                                "की शहीदों का जो खून बहा था \n" +
                                "वो मेरी और आपकी नींद के लिए था !!",

                        "बस यह बात हवाओं को बताए रखना \n" +
                                "रोशनी होगी चिरागो को जलाए रखना\n" +
                                "लहू देकर भी जिसकी हिफाजत की शहीदों ने\n" +
                                "उस तिरंगे को सदा दिल में बसाए रखना!!"





                };


        ma= new MyAdapter(Deshbhaktimsg,DeshbhaktiShayari.this);
        viewpager.setAdapter(ma);






        //for showing count of shayari on top....by sumit.....
        shayaricount = findViewById(R.id.count);
        //default set to 1
        shayaricount.setText(String.format("Deshbhakti Shayari : %d/%d", viewpager.getCurrentItem() + 1, Deshbhaktimsg.length));
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @SuppressLint("DefaultLocale")
            @Override
            public void onPageSelected(int position) {

                shayaricount.setText(String.format("Deshbhakti Shayari : %d/%d", position+1 , Deshbhaktimsg.length));
            }


            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



        // next button
        next=(ImageButton)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos=viewpager.getCurrentItem();
                if ((pos+1)==ma.getCount()){
                    viewpager.setCurrentItem(0);
                }else{
                    viewpager.setCurrentItem(pos+1);
                }
            }
        });



        //previous button
        previous=(ImageButton)findViewById(R.id.previous);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos=viewpager.getCurrentItem();
                if (pos==0){
                    viewpager.setCurrentItem(ma.getCount());
                }else{
                    viewpager.setCurrentItem(pos-1);
                }
            }
        });


        //for whatsapp
        whatsapp = findViewById(R.id.whatsapp);
        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                whatsappIntent.setType("text/plain");
                whatsappIntent.setPackage("com.whatsapp");
                whatsappIntent.putExtra(Intent.EXTRA_TEXT, Deshbhaktimsg[viewpager.getCurrentItem()]);
                try {
                    startActivity(whatsappIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getBaseContext(),"Whatsapp Not Installed",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //for copy
        copy = findViewById(R.id.copy);
        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("msg", Deshbhaktimsg[viewpager.getCurrentItem()]);
                assert clipboard != null;
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getBaseContext(),"Copied To Clipboard !",Toast.LENGTH_SHORT).show();
            }
        });

        // for addddddd
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    //for navigation bar
    private void setUpToolbar(){
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer1);
        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }
}
