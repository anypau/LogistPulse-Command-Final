package logistpulse.state;

public final class ReservedState implements LotState {
    public String name() { return "RESERVED"; }
    public boolean canPick() { return false; }
    public LotState reserve() { throw new IllegalStateException("Already reserved"); }
    public LotState release() { return new AvailableState(); }
    public LotState quarantine() { return new QuarantinedState(); }
}
