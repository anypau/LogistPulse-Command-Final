package logistpulse.state;

public final class AvailableState implements LotState {
    public String name() { return "AVAILABLE"; }
    public boolean canPick() { return true; }
    public LotState reserve() { return new ReservedState(); }
    public LotState release() { throw new IllegalStateException("Lot is not reserved"); }
    public LotState quarantine() { return new QuarantinedState(); }
}
