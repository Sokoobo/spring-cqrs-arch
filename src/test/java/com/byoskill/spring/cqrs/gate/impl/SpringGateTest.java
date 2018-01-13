package com.byoskill.spring.cqrs.gate.impl;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.concurrent.CompletableFuture;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.byoskill.spring.cqrs.gate.api.IEventBusService;

@RunWith(MockitoJUnitRunner.class)
public class SpringGateTest {
    private static final String COMMAND = "GNI";


    @Mock
    private CommandExecutorService commandExecutorService;

    @Mock
    private IEventBusService eventBus;

    @InjectMocks
    private SpringGate springGate;

    @Test
    public void testDispatch() throws Exception {
	when(commandExecutorService.run(COMMAND, Object.class)).thenReturn(CompletableFuture.supplyAsync(() -> null));
	springGate.dispatch(COMMAND);
	verify(commandExecutorService, Mockito.times(1)).run(COMMAND, Object.class);
    }

    @Test
    public void testDispatchAsync() throws Exception {
	when(commandExecutorService.run(COMMAND, Object.class)).thenReturn(CompletableFuture.supplyAsync(() -> null));
	springGate.dispatchAsync(COMMAND);
	verify(commandExecutorService, Mockito.times(1)).run(COMMAND, Object.class);
    }


    @Test
    public void testDispatchExpect() throws Exception {
	when(commandExecutorService.run(COMMAND, String.class)).thenReturn(CompletableFuture.supplyAsync(() -> null));
	springGate.dispatch(COMMAND, String.class);
	verify(commandExecutorService, Mockito.times(1)).run(COMMAND, String.class);
    }

    @Test
    public void testDispatchExpectAsync() throws Exception {
	when(commandExecutorService.run(COMMAND, String.class)).thenReturn(CompletableFuture.supplyAsync(() -> null));
	springGate.dispatchAsync(COMMAND, String.class);
	verify(commandExecutorService, Mockito.times(1)).run(COMMAND, String.class);
    }

}
