package de.rweber.wishlist.command_side.domain_model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import de.rweber.wishlist.command_side.events.Event;

public abstract class AggregateRoot {

    private final List<Event> changes = new ArrayList<Event>();
    private int version;
    
    public abstract UUID getId();
    
    public int getVersion()
    {
    	return version;
    }

    public List<Event> getUncommittedChanges()
    {
        return changes;
    }

    public void markChangesAsCommitted()
    {
        changes.clear();
    }

    public abstract void loadsFromHistory(List<Event> history);

    protected void applyChange(Event event, boolean isNew)
    {
    	changes.add(event);
    }
}
