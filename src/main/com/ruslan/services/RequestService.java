package com.ruslan.services;

import com.ruslan.data.repository.BookRepository;
import com.ruslan.data.repository.RequestRepository;
import com.ruslan.data.request.Request;
import com.ruslan.services.sinterface.IRequestService;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class RequestService implements IRequestService {

    private final RequestRepository requestRepository;
    private final BookRepository bookRepository;

    private static final String fileCSV = "Requests.csv";

    public RequestService(RequestRepository requestRepository, BookRepository bookRepository) {
        this.requestRepository = requestRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void createRequest(int bookId) {
        requestRepository.saveRequest(new Request(bookRepository.getById(bookId)));
    }

    public void cancelRequestsById(int requestId) {
        requestRepository.removeRequest(requestId);
        System.out.println("Request id=" + requestId + " canceled");
    }

    public void cancelRequestsByBookId(int bookId) {
        Optional.of(requestRepository.getRequestForBook(bookId)).ifPresent(request -> {
            requestRepository.removeRequest(request.getId());
        });
    }

    //List of book requests (sort by number of requests, alphabetically);
    public List<Request> getRequestSortedByNumber() {
        List<Request> listReq = requestRepository.getRequestList();
        listReq.sort(Comparator.comparing(Request::getId));
        return listReq;
    }

    public List<Request> getRequestSortedByAlphabetically() {
        List<Request> listReq = requestRepository.getRequestList();
        listReq.sort(Comparator.comparing(request -> request.getBook().getTitle()));
        return listReq;
    }


    public void writeRequestToFile(int id) {
        File requestFile = new File(fileCSV);
        FileOutputStream fos;
        ObjectOutputStream oos;
        List<Request> requestsList;


        while (true) {
            try {
                requestFile.createNewFile(); // if file already exists will do nothing
                fos = new FileOutputStream(requestFile);
                oos = new ObjectOutputStream(fos);

                requestsList = getRequestListFromFile();
                requestsList.add(RequestRepository.getInstance().getById(id));
                oos.writeObject(requestsList);
                oos.close();
                break;
            } catch (FileNotFoundException e) {
                System.out.println("File not created. Please repeat operation");
                break;
            } catch (NullPointerException e) {
                System.out.println("Couldn't get List from file or crete new List");
                break;
            } catch (IOException e) {
                System.out.println("Could not write to file");
                e.printStackTrace();
                break;
            }
        }
    }

    public List<Request> getRequestListFromFile() {
        FileInputStream fis;
        ObjectInputStream ois;
        List<Request> requestList;
        File requestFile = new File(fileCSV);
        while (true) {
            try {
                fis = new FileInputStream(fileCSV);
                ois = new ObjectInputStream(fis);
                requestList = (List<Request>) ois.readObject();
                ois.close();
                return requestList;
            } catch (ClassNotFoundException e) {
                System.out.println("Data received in unknown format");
                e.printStackTrace();
                break;
            } catch (EOFException e) {
                System.out.println("File Books.csv was empty");
                return requestList = new ArrayList<>();
            } catch (StreamCorruptedException e) {
                System.out.println("Uncorrected data in file. Data was erased");
                requestFile.delete();
                return requestList = new ArrayList<>();
            } catch (IOException e) {
                System.out.println("Could not read from file");
                e.printStackTrace();
                break;
            }
        }
        return null;
    }

    public Request getRequestFromFile(int id) {
        return getRequestListFromFile()
                .stream()
                .filter(request ->
                        request.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
