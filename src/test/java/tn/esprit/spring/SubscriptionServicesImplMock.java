package tn.esprit.spring;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.TypeSubscription;
import tn.esprit.spring.repositories.ISkierRepository;
import tn.esprit.spring.repositories.ISubscriptionRepository;
import tn.esprit.spring.services.SubscriptionServicesImpl;

import java.time.LocalDate;
import java.util.*;

@ExtendWith(MockitoExtension.class)
public class SubscriptionServicesImplMock {

    @InjectMocks
    private SubscriptionServicesImpl subscriptionServices;

    @Mock
    private ISubscriptionRepository subscriptionRepository;

    @Mock
    private ISkierRepository skierRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddSubscription() {
        // Create a Subscription object for testing
        Subscription subscription = new Subscription();
        subscription.setTypeSub(TypeSubscription.ANNUAL);
        subscription.setStartDate(LocalDate.now());

        // Mock the behavior of the repository
        Mockito.when(subscriptionRepository.save(Mockito.any(Subscription.class))).thenReturn(subscription);

        // Call the method to be tested
        Subscription result = subscriptionServices.addSubscription(subscription);

        // Assertions
        Mockito.verify(subscriptionRepository, Mockito.times(1)).save(subscription);
        // You can add more specific assertions on the result if needed
    }

    @Test
    public void testUpdateSubscription() {
        // Create a Subscription object for testing
        Subscription subscription = new Subscription();
        subscription.setTypeSub(TypeSubscription.MONTHLY);
        subscription.setStartDate(LocalDate.now());

        // Mock the behavior of the repository
        Mockito.when(subscriptionRepository.save(Mockito.any(Subscription.class))).thenReturn(subscription);

        // Call the method to be tested
        Subscription result = subscriptionServices.updateSubscription(subscription);

        // Assertions
        Mockito.verify(subscriptionRepository, Mockito.times(1)).save(subscription);
        // You can add more specific assertions on the result if needed
    }

    @Test
    void testRetrieveSubscriptionById() {
        // Create a Subscription object for testing
        Subscription subscription = new Subscription();
        subscription.setNumSub(1L);

        // Mock the behavior of the repository
        Mockito.when(subscriptionRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(subscription));

        // Call the method to be tested
        Subscription result = subscriptionServices.retrieveSubscriptionById(1L);

        // Assertions
        Mockito.verify(subscriptionRepository, Mockito.times(1)).findById(1L);
        // You can add more specific assertions on the result if needed
    }

    @Test
    public void testGetSubscriptionByType() {
        // Create a TypeSubscription for testing
        // Create a TypeSubscription for testing
        TypeSubscription type = TypeSubscription.MONTHLY;

// Create a set of Subscription objects for testing
        Set<Subscription> subscriptions = new HashSet<>(Collections.singletonList(new Subscription()));

// Mock the behavior of the repository
        Mockito.when(subscriptionRepository.findByTypeSubOrderByStartDateAsc(type)).thenReturn(subscriptions);

        // Call the method to be tested
        Set<Subscription> result = subscriptionServices.getSubscriptionByType(type);

        // Assertions
        Mockito.verify(subscriptionRepository, Mockito.times(1)).findByTypeSubOrderByStartDateAsc(type);
        // You can add more specific assertions on the result if needed
    }

    @Test
    public void testRetrieveSubscriptionsByDates() {
        // Create start and end dates for testing
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 31);

        // Create a list of Subscription objects for testing
        List<Subscription> subscriptions = Collections.singletonList(new Subscription());

        // Mock the behavior of the repository
        Mockito.when(subscriptionRepository.getSubscriptionsByStartDateBetween(startDate, endDate)).thenReturn(subscriptions);

        // Call the method to be tested
        List<Subscription> result = subscriptionServices.retrieveSubscriptionsByDates(startDate, endDate);

        // Assertions
        Mockito.verify(subscriptionRepository, Mockito.times(1)).getSubscriptionsByStartDateBetween(startDate, endDate);
        // You can add more specific assertions on the result if needed
    }}