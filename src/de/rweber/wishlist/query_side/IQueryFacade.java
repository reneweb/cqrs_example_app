package de.rweber.wishlist.query_side;

import java.util.List;

import de.rweber.wishlist.query_side.dto.WishListDto;

public interface IQueryFacade {

    List<WishListDto> GetWishLists();
}
