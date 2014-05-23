package de.rweber.wishlist.event_store;

import java.util.List;
import java.util.UUID;

import de.rweber.wishlist.command_side.events.Event;

public interface IEventStore
{
    void SaveEvents(UUID aggregateId, List<Event> events, int expectedVersion);
    List<Event> GetEventsForAggregate(UUID aggregateId);
}
