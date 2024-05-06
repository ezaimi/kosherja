package com.example.kosherja.Controller.UserController;

import com.example.kosherja.Model.Facilities.CinemaRoom;
import com.example.kosherja.Model.Facilities.Seat;
import com.example.kosherja.Model.Facilities.SeatStatus;
import com.example.kosherja.Repo.FacilitiesRepo.SeatRepo;
import com.example.kosherja.Service.Cinema.CinemaRoomService;
import com.example.kosherja.Service.UserService.ManagerMovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ManagerMovieControllerTest {

    @InjectMocks
    private ManagerMovieController managerMovieController;

    @Mock
    private ManagerMovieService managerMovieService;

    @Mock
    private SeatRepo seatRepo;

    @Mock
    private CinemaRoomService cinemaRoomService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAssignMovieandTime() {
        String movieId = "123";
        String roomId = "456";
        CinemaRoom cinemaRoom1 = new CinemaRoom();
        cinemaRoom1.setShowtime(List.of("9:00 AM", "12:00 PM"));

        // Mocking seats creation
        Seat[][] seats = new Seat[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Seat seat = new Seat();
                seat.setStatus(SeatStatus.AVAILABLE);
                seat.setRow(i);
                seat.setColumn(j);
                seat.setMovieId(movieId);
                seats[i][j] = seat;
            }
        }

        // Mocking seatRepo.save()
        doNothing().when(seatRepo).save(any(Seat.class));

        // Mocking cinemaRoomService.createCinemaRoom()
        CinemaRoom cinemaRoom = new CinemaRoom(movieId);
        cinemaRoom.setMovieId(movieId);
        cinemaRoom.setShowtime(cinemaRoom1.getShowtime());
        cinemaRoom.setSeats(seats);
        when(cinemaRoomService.createCinemaRoom(any(CinemaRoom.class))).thenReturn(cinemaRoom);

        CinemaRoom result = managerMovieController.assignMovieandTime(movieId, cinemaRoom1);

        // Verifying seatRepo.save() is called for each seat
        verify(seatRepo, times(25)).save(any(Seat.class));

        // Verifying cinemaRoomService.createCinemaRoom() is called once with the expected CinemaRoom object
        verify(cinemaRoomService, times(1)).createCinemaRoom(cinemaRoom);

        // Verifying the result
        assertEquals(cinemaRoom, result);
    }
}
