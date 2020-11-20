package swapp.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import swapp.core.*;
import swapp.json.SwappModule;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.nio.charset.StandardCharsets;

public class RemoteSwappAccess implements SwappAccess {

  private final URI endpointURI;
  private ObjectMapper objectMapper;
  private Swapp swapp;

  public RemoteSwappAccess(URI endpointURI) {
    this.endpointURI = endpointURI;
    this.objectMapper = new ObjectMapper().registerModule(new SwappModule());
  }

  private URI swappURIResolver(String param) {
    return endpointURI.resolve(uriParam(param));
  }

  private String uriParam(String s) {
    return URLEncoder.encode(s, StandardCharsets.UTF_8);
  }

  private Swapp getSwapp() {
    if (swapp == null) {
      HttpRequest request = HttpRequest.newBuilder(endpointURI)
              .header("Accept", "application/json")
              .header("Content-type", "application/json")
              .GET()
              .build();
      try {
        final HttpResponse<String> response =
                HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
        final String responseString = response.body();
        this.swapp = objectMapper.readValue(responseString, Swapp.class);
        System.out.println("Swapp: " + this.swapp);
      } catch (IOException | InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
    return swapp;
  }

  @Override
  public User getUser(String name) {
    try {
      HttpRequest request = HttpRequest.newBuilder(swappURIResolver("user/" + name))
              .header("Accept", "application/json")
              .header("Content-type", "application/json")
              .GET()
              .build();
      final HttpResponse<String> response =
              HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
      String responseString = response.body();
      return objectMapper.readValue(responseString, User.class);
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void createUser(String name, String email, String password) {
    try {
      User user = new User(name, email, password);
      String json = objectMapper.writeValueAsString(user);
      System.out.println(swappURIResolver("adduser"));
      HttpRequest request = HttpRequest.newBuilder(swappURIResolver("adduser"))
              .header("Accept", "application/json")
              .header("Content-Type", "application/json")
              .POST(BodyPublishers.ofString(json))
              .build();
      final HttpResponse<String> response =
              HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
      String responseString = response.body();
      Boolean added = objectMapper.readValue(responseString, Boolean.class);
      if (added != null) {
        swapp.createUser(name, email, password);
      }
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void createAd(String title, String textBody, Ad.Category category) {
    try {
      HttpRequest request = HttpRequest.newBuilder(swappURIResolver("createad"))
              .header("Accept", "application/json")
              .header("Content-Type", "application/json")
              .POST(BodyPublishers.ofString(title + "-" + textBody + "-" + category))
              .build();
      final HttpResponse<String> response =
              HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
      String responseString = response.body();
      boolean bool = objectMapper.readValue(responseString, Boolean.class);
      if (bool) {
        swapp.createAd(title, textBody, category);
      }
    } catch (IOException | InterruptedException e) {
        throw new RuntimeException(e);
    }
  }

  @Override
  public boolean loginUser(String email, String password) {
    try {
      HttpRequest request = HttpRequest.newBuilder(swappURIResolver("login"))
              .header("Accept", "application/json")
              .header("Content-Type", "application/json")
              .PUT(BodyPublishers.ofString(email + "-" + password))
              .build();
      final HttpResponse<String> response =
              HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
      String responseString = response.body();
      User user  = swapp.getUserLogin(email, password);
      if (user != null && objectMapper.readValue(responseString, Boolean.class)) {
        swapp.setCurrentUser(user);
        return true;
      }
      return false;
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public UserValidation getUserValidation() {
    return getSwapp().getUserValidation();
  }

  @Override
  public User getCurrentUser() {
    return getSwapp().getCurrentUser();
  }

  @Override
  public void setCurrentUser(User user) {
    getSwapp().setCurrentUser(user);
  }

  @Override
  public AdList getAdList() {
    return getSwapp().getAdList();
  }

  @Override
  public void populateAdList() {
    getSwapp().populateAdList();
  }

  @Override
  public void createTransaction(Ad ad, User requester) {
    getSwapp().createTransaction(ad, requester);
  }

  @Override
  public Boolean changeAdStatus(Ad ad, Ad.Status status) {
    return getSwapp().setAdStatus(ad, status);
  }

  @Override
  public Boolean setTransactionStatus(Transaction transaction) {
    return getSwapp().setTransactionStatus(transaction);
  }
}
