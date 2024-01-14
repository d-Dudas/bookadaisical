export enum TradingOption {
    ALL = 'ALL',
    CURRENCY = 'CURRENCY',
    POINTS = 'POINTS',
    SWAP = 'SWAP'
}

export function convertToTradingOption(value: string): TradingOption {
    return TradingOption[value as keyof typeof TradingOption];
}
