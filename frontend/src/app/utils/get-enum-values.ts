export function getEnumValues(e: any) {
    return Object.keys(e).filter(key => isNaN(Number(key)));
}
