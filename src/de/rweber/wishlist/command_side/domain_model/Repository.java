package de.rweber.wishlist.command_side.domain_model;

import java.util.List;
import java.util.UUID;

import de.rweber.wishlist.command_side.events.Event;
import de.rweber.wishlist.event_store.IEventStore;

public class Repository<T extends AggregateRoot> implements IRepository<T> {
	
	private Class<T> clazz;
	private final IEventStore storage;
	
	public static <T extends AggregateRoot> Repository<T> getRepository(Class<T> clazz, IEventStore storage)
	{
		return new Repository<T>(clazz, storage);
	}
	
	Repository(Class<T> clazz, IEventStore storage) 
	{
		this.clazz = clazz;
		this.storage = storage;
	}

	@Override
	public void Save(AggregateRoot aggregate, int expectedVersion)
	{
		storage.SaveEvents(aggregate.getId(), aggregate.getUncommittedChanges(), expectedVersion);
	}

	@Override
	public T GetById(UUID id) 
	{
		try {
			T obj = clazz.newInstance();
			List<Event> e = storage.GetEventsForAggregate(id);
	        obj.loadsFromHistory(e);
	        return obj;
		} 
		catch (InstantiationException | IllegalAccessException e1) 
		{
			e1.printStackTrace();
			return null;
		}
	}

}
