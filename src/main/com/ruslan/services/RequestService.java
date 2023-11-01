package com.ruslan.services;

import com.ruslan.DAO.IRequestDao;
import com.ruslan.DI.annotation.Inject;
import com.ruslan.data.repository.rinterface.IBookRepository;
import com.ruslan.data.repository.rinterface.IRequestRepository;
import com.ruslan.data.request.Request;
import com.ruslan.jsonHandlers.JsonReader;
import com.ruslan.jsonHandlers.JsonWriter;
import com.ruslan.services.sinterface.IRequestService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class RequestService implements IRequestService {

    public static final Logger logger = LogManager.getLogger();
    private static final String fileName = "Requests.csv";
    public static final String pathRequestJSON = "src\\main\\resources\\Requests.json";

    @Inject
    private IRequestDao requestDao;
    private final JsonWriter jsonWriter = JsonWriter.getInstance();
    private final JsonReader jsonReader = JsonReader.getInstance();

    public RequestService() {
    }

    @Override
    public void createRequest(Request request) {
        requestDao.saveRequest(request);
    }

    public void cancelRequestsById(int requestId) {
        requestDao.removeByRequestId(requestId);
    }

    public void cancelRequestsByBookId(int bookId) {
        requestDao.removeByBookId(bookId);
    }

    //List of book requests (sort by number of requests, alphabetically);
    public List<Request> getRequestSortedByNumber() {
        List<Request> listReq = requestDao.getRequestsList();
        listReq.sort(Comparator.comparing(Request::getId));
        return listReq;
    }

    public List<Request> getRequestSortedByAlphabetically() {
        List<Request> listReq = requestDao.getRequestsList();
        listReq.sort(Comparator.comparing(request -> request.getBook().getTitle()));
        return listReq;
    }

    public void writeRequestToFile(int id) {
        File requestFile = new File(fileName);
        FileOutputStream fos;
        ObjectOutputStream oos;
        List<Request> requestsList;

        try {
            requestFile.createNewFile(); // if file already exists will do nothing
            requestsList = getRequestListFromFile();
            requestsList.add(requestDao.findById(id).orElse(null));
            fos = new FileOutputStream(requestFile);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(requestsList);
            oos.close();
        } catch (NullPointerException e) {
            System.out.println("Could not get data from file.");
            logger.error("Could not get data to file", e);
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please repeat operation");
            logger.error("File not found", e);
        } catch (IOException e) {
            System.out.println("Could not write data to file");
            logger.error("Could not write data to file", e);
        }
    }

    public List<Request> getRequestListFromFile() {
        FileInputStream fis;
        ObjectInputStream ois;
        List<Request> requestList;

        try {
            fis = new FileInputStream(fileName);
            ois = new ObjectInputStream(fis);
            requestList = (List<Request>) ois.readObject();
            ois.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Class 'Request' not found!");
            logger.error("Class 'Request' not found!", e);
            return null;
        } catch (EOFException e) {
            System.out.println("File Requests.csv was empty");
            return requestList = new ArrayList<>();
        } catch (StreamCorruptedException e) {
            System.out.println("Uncorrected data in file. Data was erased");
            return requestList = new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Could not read data from file");
            logger.error("Could not read data to file", e);
            return null;
        }
        return requestList;
    }

    public Request getRequestFromFile(int id) {
        return getRequestListFromFile()
                .stream()
                .filter(request ->
                        request.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void importRequestsFromJsonToDataBase() {
        List<Request> requestsList = jsonReader.readEntities(Request.class, pathRequestJSON);
        requestsList.forEach(request -> requestDao.saveRequest(request));
    }

    public void exportRequestsToJson() {
        jsonWriter.writeEntities(requestDao.getRequestsList(), pathRequestJSON);
    }
}
