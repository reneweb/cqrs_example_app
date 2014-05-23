package de.rweber.wishlist.command_side.domain_model;

import java.util.UUID;

public interface IRepository<T> {

	void Save(AggregateRoot aggregate, int expectedVersion);
    T GetById(UUID id);
}
