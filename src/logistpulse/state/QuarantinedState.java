package logistpulse.state;

public final class QuarantinedState implements LotState {
    public String name() { return "QUARANTINED"; }
    public boolean canPick() { return false; }
    public LotState reserve() { throw new IllegalStateException("Quarantined lot cannot be reserved"); }
    public LotState release() { throw new IllegalStateException("Quarantined lot cannot be released"); }
    public LotState quarantine() { return this; }
}
