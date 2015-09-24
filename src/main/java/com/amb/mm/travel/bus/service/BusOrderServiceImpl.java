package com.amb.mm.travel.bus.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.amb.mm.travel.bus.BusBookingDto;
import com.amb.mm.travel.bus.BusOffer;
import com.amb.mm.travel.bus.BusOrder;
import com.amb.mm.travel.bus.BusOrderItem;
import com.amb.mm.travel.bus.BusOrderDto;
import com.amb.mm.travel.core.Customer;
import com.amb.mm.travel.core.OrderStatusType;
import com.amb.mm.travel.core.service.CustomerRepository;

@Component("busOrderService")
@Transactional
public class BusOrderServiceImpl implements BusOrderService{

	private final BusOrderRepository orderRepo;
	
	private final BusOfferRepository offerRepo;
	
	private final CustomerRepository customerRepo;
	
//	@Autowired
//	private final MailingService mailer;
    
	@Autowired
	public BusOrderServiceImpl(BusOrderRepository orderRepository, BusOfferRepository offerRepository, CustomerRepository customerRepository) {
		this.orderRepo = orderRepository;
		this.offerRepo = offerRepository;
		this.customerRepo = customerRepository;
//		this.mailer = mailer;
	}
	
	@Override
	@Transactional
	public BusOrderDto placeOrder(BusOrderDto dto) {
		Long offerId = dto.getOffer().getId();
		if (offerId != null) {
			BusOffer offer = this.offerRepo.findOne(offerId);
			if (offer != null) {
				final BusOrder order = new BusOrder(dto.getBookingRefId(), offer, this.customerRepo.save(dto.getCustomer()), dto.getComment(), OrderStatusType.OPENED);
				for(BusOrderItem item : dto.getTickets()) {
					order.addOrderItem(new BusOrderItem(this.customerRepo.save(item.getPassenger()), item.getPlannedTravelDate(), OrderStatusType.OPENED));
				}
				
				// send email to customer
				//mailer.sendConfirmationEmail(order);
				//TODO: to send email
				
				return convertOrderToDto(this.orderRepo.save(order));
			}
		}

		return null;
	}
	
	@Override
	@Transactional
	public String bookOrders(BusBookingDto booking) {
		final String bookingRefId = UUID.randomUUID().toString();
		
		final Customer buyer = this.customerRepo.save(booking.getBuyer());
		
		for(BusOrderDto dto : booking.getOrders()) {
			dto.setCustomer(buyer);
			dto.setBookingRefId(bookingRefId);
			
			placeOrder(dto);
		}

		return bookingRefId;
	}
	
	@Override
	@Transactional
	public BusBookingDto book(BusBookingDto booking) {
		BusBookingDto newBooking = new BusBookingDto();
		final String bookingRefId = UUID.randomUUID().toString();
		
		final Customer buyer = this.customerRepo.save(booking.getBuyer());
		
		newBooking.setBuyer(buyer);
		
		for(BusOrderDto dto : booking.getOrders()) {
			dto.setCustomer(buyer);
			dto.setBookingRefId(bookingRefId);
			
			newBooking.getOrders().add(placeOrder(dto));
			newBooking.setBookingRefId(bookingRefId);
		}

		return newBooking;
	}
	
	private BusOrderDto convertOrderToDto(BusOrder order) {
		BusOrderDto dto = new BusOrderDto(order.getBookingRefId(), order.getBusOffer(), order.getCustomer(), new ArrayList<BusOrderItem>(order.getOrderItems()), order.getComment(), order.getUnitPrice(), order.getTotalPrice());
		return dto;
	}
	
	@Override
	public boolean cancelOrder(Long orderId) {
		Assert.notNull(orderId, "Order ID must not be null");
		
		BusOrder order = this.orderRepo.findOne(orderId);
		if (order != null) {
			order.setOrderStatus(OrderStatusType.CANCELLED);
			order = this.orderRepo.save(order);
			return true;
		}
		
		return false;
	}

	@Override
	public boolean changeOrder(Long orderId, BusOrderDto order) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<BusOrderDto> findOrdersByCustomer(Long customerId) {
		List<BusOrderDto> orderDtos = new ArrayList<BusOrderDto>();
		Customer customer = this.customerRepo.findOne(customerId);
		if (customer != null) {
			List<BusOrder> orders = this.orderRepo.findByCustomer(customer);
			if (orders != null && !orders.isEmpty()) {
				for(BusOrder o : orders) {
					orderDtos.add(convertOrderToDto(o));
				}
			}
		}
		return orderDtos;
	}

	@Override
	public List<BusOrderDto> findOrdersByBookingRefId(String bookingRefId) {
		List<BusOrderDto> orderDtos = new ArrayList<BusOrderDto>();
		List<BusOrder> orders = this.orderRepo.findByBookingRefId(bookingRefId);
		if (orders != null && !orders.isEmpty()) {
			for(BusOrder o : orders) {
				orderDtos.add(convertOrderToDto(o));
			}
		}
		return orderDtos;
	}

	@Override
	public BusOrderDto findOne(Long orderId) {
		BusOrder order = this.orderRepo.findOne(orderId);
		if (order != null) {
			return convertOrderToDto(order);
		}
		return null;
	}

//	public void sendConfirmationEmail(final BusOrder order) {
//        MimeMessagePreparator preparator = new MimeMessagePreparator() {
//            public void prepare(MimeMessage mimeMessage) throws Exception {
//                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
//                message.setTo(order.getCustomer().getEmailAddress());
////                message.setFrom("webmaster@csonth.gov.uk"); // could be parameterized...
//                Map model = new HashMap();
//                model.put("order", order);
//                
//                String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "order.confirmation.vm", "UTF-8" , model);
//                
//                message.setText(text, true);
//            }
//        };
//        this.mailSender.send(preparator);
//    }
	
	
}
