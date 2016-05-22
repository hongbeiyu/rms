package rms;

public class Room {
	private Long id;
	private String code;//房间编号    31#701   3套 东边套，中间套，西边套
	private String address;
	private Double size;
	private Double price;
	public Room(){
	
	}
	public Room(Long id, String code, String address, Double size, Double price) {
		super();
		this.id = id;
		this.code = code;
		this.address = address;
		this.size = size;
		this.price = price;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Double getSize() {
		return size;
	}
	public void setSize(Double size) {
		this.size = size;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Room [id=" + id + ", code=" + code + ", address=" + address
				+ ", size=" + size + ", price=" + price + "]";
	}
}

