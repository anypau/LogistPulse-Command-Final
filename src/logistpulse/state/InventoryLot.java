package logistpulse.state;

import java.time.LocalDate;
import java.util.Objects;

public final class InventoryLot {
    private final String id;
    private final String product;
    private final LocalDate receivedAt;
    private final LocalDate expiresAt;
    private final int quantity;
    private LotState state = new AvailableState();

    public InventoryLot(String id, String product, int quantity, LocalDate receivedAt, LocalDate expiresAt) {
        if (id == null || id.isBlank() || product == null || product.isBlank() || quantity <= 0)
            throw new IllegalArgumentException("Invalid lot");
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.receivedAt = Objects.requireNonNull(receivedAt);
        this.expiresAt = Objects.requireNonNull(expiresAt);
        if (expiresAt.isBefore(receivedAt)) throw new IllegalArgumentException("Expiry precedes reception");
    }

    public String getId() { return id; }
    public String getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public LocalDate getReceivedAt() { return receivedAt; }
    public LocalDate getExpiresAt() { return expiresAt; }
    public String getState() { return state.name(); }
    public boolean canPick() { return state.canPick(); }
    public void reserve() { state = state.reserve(); }
    public void release() { state = state.release(); }
    public void quarantine() { state = state.quarantine(); }
    @Override public String toString() { return id + " " + product + " qty=" + quantity + " [" + state.name() + "]"; }
}
