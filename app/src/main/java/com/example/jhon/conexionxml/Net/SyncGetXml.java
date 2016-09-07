package com.example.jhon.conexionxml.Net;

import android.os.AsyncTask;
import android.provider.DocumentsContract;

import com.example.jhon.conexionxml.Models.Cities;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by jhon on 6/09/16.
 */
public class SyncGetXml {
    String url;
    Cities cities;
    AsyncTask<Void,Void,Void> asyncTask;
    OnSyncListener onSyncListener;
    List<Cities> data;

    public static final int SYNC_CORRECT = 0;
    public static final int SYNC_FAILED = 1;



    public interface OnSyncListener{
        void OnPrepareConection(int state);
        void OnFinishedConection(int state, List<Cities> cities, String e);
    }

    public SyncGetXml(String url, Cities cities, OnSyncListener onSyncListener) {
        this.url = url;
        this.cities = cities;
        this.onSyncListener = onSyncListener;
    }

    public void conectinoWithServer(){
        asyncTask = new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                onSyncListener.OnPrepareConection(SYNC_CORRECT);
                data = new ArrayList<>();
            }

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    URL urlRemote =  new URL(url);
                    DocumentBuilderFactory dbf =  DocumentBuilderFactory.newInstance();
                    DocumentBuilder db =  dbf.newDocumentBuilder();
                    Document doc = db.parse(new InputSource(urlRemote.openStream()));
                    doc.getDocumentElement().normalize();

                    NodeList nodeList = doc.getElementsByTagName("string");
                    Element element = (Element) node1;
                    NodeList nodeList1 = element.getElementsByTagName("NewDataSet");
                    for (int i = 0 ; i < nodeList.getLength(); i++){
                        Node node =  nodeList.item(i);
                        Element firstElement = (Element) node;
                        NodeList citiesElements = firstElement.getElementsByTagName("Country");
                        Cities city = new Cities();
                        city.setCity(citiesElements.item(0).getNodeValue());
                        data.add(city);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                onSyncListener.OnFinishedConection(SYNC_CORRECT,data,null);
            }
        }.execute();

    }
}
