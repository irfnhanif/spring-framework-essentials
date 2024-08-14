package accounts.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import rewards.internal.restaurant.JpaRestaurantRepository;
import rewards.internal.restaurant.RestaurantRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

public class RestaurantHealthCheckTest {
	private RestaurantHealthCheck restaurantHealthCheck;
	private RestaurantRepository restaurantRepository;

	@BeforeEach
	public void setUp() {
		restaurantRepository = mock(JpaRestaurantRepository.class);
		restaurantHealthCheck = new RestaurantHealthCheck(restaurantRepository);
	}

	@Test
	public void testHealthReturnsUpIfThereAreRestaurants() {
		// Mock the Repository so getRestaurantCount returns 1
		doReturn(1L).when(restaurantRepository).getRestaurantCount();

		// Invoke the health() method on RestaurantHealthCheck object
		Health result = restaurantHealthCheck.health();

		// Health check should return UP
		verify(restaurantRepository).getRestaurantCount();
		assertThat(result.getStatus()).isEqualTo(Status.UP);
	}

	@Test
	public void testHealthReturnsDownIfThereAreNoRestaurants() {
		// Mock the Repository so getRestaurantCount returns 0
		doReturn(0L).when(restaurantRepository).getRestaurantCount();

		// Invoke the health() method on RestaurantHealthCheck object
		Health result = restaurantHealthCheck.health();

		// Health check should return DOWN
		verify(restaurantRepository).getRestaurantCount();
		assertThat(result.getStatus()).isEqualTo(Status.DOWN);
	}
}