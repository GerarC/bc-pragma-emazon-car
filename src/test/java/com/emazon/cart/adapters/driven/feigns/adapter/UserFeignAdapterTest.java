package com.emazon.cart.adapters.driven.feigns.adapter;

import com.emazon.cart.adapters.driven.feigns.client.UserFeign;
import com.emazon.cart.adapters.driven.feigns.dto.response.ExistsUserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserFeignAdapterTest {
    @Mock
    UserFeign userFeign;

    @InjectMocks
    UserFeignAdapter userFeignAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void existsUserById() {
        String userId = "userId";
        ExistsUserResponse userResponse = new ExistsUserResponse(true);
        when(userFeign.existUser(userId)).thenReturn(userResponse);
        boolean exists = userFeignAdapter.existsUserById(userId);
        verify(userFeign).existUser(userId);
        assertTrue(exists);
    }
}