package com.gaurav.optym.gsearch.feeder.source;

import com.gaurav.optym.gsearch.feeder.domain.Joke;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = "com.gaurav.optym.gsearch.feeder.*")
public class OfficialJokeProviderTest {

    @Test
    public void getData() throws Exception {
        OfficialJokeProvider officialJokeProviderMock = mock(OfficialJokeProvider.class);
        whenNew(OfficialJokeProvider.class).withNoArguments().thenReturn(officialJokeProviderMock);

        Joke joke = new Joke();
        joke.setId(1L);
        joke.setPunchline("hello");
        joke.setType("knock-knock");
        OfficialJokeProvider officialJokeProvider = new OfficialJokeProvider();
        when(officialJokeProvider.getData()).thenReturn(joke);
        Joke fetchedJoke = officialJokeProvider.getData();
        Assert.assertNotNull(fetchedJoke);
    }
}