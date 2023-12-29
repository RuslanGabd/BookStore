package com.ruslan.services;


import com.ruslan.database.DAO.BookRepository;
import com.ruslan.database.DAO.RequestRepository;
import com.ruslan.dto.RequestDto;
import com.ruslan.entity.request.Request;
import com.ruslan.json.JsonReader;
import com.ruslan.json.JsonWriter;
import com.ruslan.services.sinterface.IRequestService;
import com.ruslan.utils.MapperRequest;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class RequestService implements IRequestService {

    public final Logger logger = LogManager.getLogger();
    private final String fileName = "Requests.csv";
    public final String pathRequestJSON = "src\\main\\resources\\Requests.json";


    private final JsonWriter jsonWriter = JsonWriter.getInstance();
    private final JsonReader jsonReader = JsonReader.getInstance();

    private final MapperRequest mapperRequest;

    private final RequestRepository requestRepository;

    private final BookRepository bookRepository;

    @Autowired
    public RequestService(MapperRequest mapperRequest, RequestRepository requestRepository, BookRepository bookRepository) {
        this.mapperRequest = mapperRequest;
        this.requestRepository = requestRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void createRequest(Request request) {
        requestRepository.save(request);
    }

    public void cancelRequestsById(int requestId) {
        requestRepository.delete(requestId);
    }

    //List of book requests (sort by number of requests, alphabetically);
    public List<Request> getRequestSortedByNumber() {
        List<Request> listReq = requestRepository.findAll();
        listReq.sort(Comparator.comparing(Request::getId));
        return listReq;
    }

    public List<Request> getRequestSortedByAlphabetically() {
        List<Request> listReq = requestRepository.findAll();
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
            requestsList.add(requestRepository.findById(id).orElse(null));
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
        requestsList.forEach(request -> requestRepository.save(request));
    }

    public void exportRequestsToJson() {
        jsonWriter.writeEntities(requestRepository.findAll(), pathRequestJSON);
    }

    public List<RequestDto> listRequestDto() {
        return requestRepository.findAll().stream()
                .map(mapperRequest::mapToRequestDto)
                .collect(toList());
    }

    public RequestDto findById(int id) {
        return mapperRequest.mapToRequestDto(
                requestRepository.findById(id)
                        .orElse(null));
    }

    public void saveRequest(RequestDto dto) {
        requestRepository.save(mapperRequest.mapToRequest(dto));
    }

    public void removeRequest(int id) {
        requestRepository.delete(id);
    }

    public List<RequestDto> RequestsDtoSortedByNumber() {
        return getRequestSortedByNumber().stream()
                .map(mapperRequest::mapToRequestDto)
                .collect(toList());
    }

    public List<RequestDto> RequestsDtoSortedByAlphabetically() {
        return getRequestSortedByAlphabetically().stream()
                .map(mapperRequest::mapToRequestDto)
                .collect(toList());
    }

    public RequestDto createRequestByBookId(Integer idBook) {
        Request request = new Request();
        request.setBook(bookRepository.findById(idBook).orElse(null));
        requestRepository.save(request);
        return mapperRequest.mapToRequestDto(request);
    }
}
