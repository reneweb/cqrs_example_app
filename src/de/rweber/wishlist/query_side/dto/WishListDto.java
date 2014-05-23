package de.rweber.wishlist.query_side.dto;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import de.rweber.wishlist.utils.Triple;

public class WishListDto  implements Serializable
{
	private static final long serialVersionUID = -88856970956462493L;
	
	private UUID id;
	private String name;
	private List<Triple<UUID, String, Double>> items;
	private int version;

    public WishListDto(UUID id, String name, List<Triple<UUID, String, Double>> items, int version)
    {
		this.setId(id);
		this.setName(name);
        this.setItems(items);
        this.setVersion(version);
    }

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Triple<UUID, String, Double>> getItems() {
		return items;
	}

	public void setItems(List<Triple<UUID, String, Double>> items) {
		this.items = items;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
}
