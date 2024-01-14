export function getEnumValues(e: any) {
    return Object.keys(e)
        .filter(key => isNaN(Number(key)))
        .map(key => key.charAt(0) + key.slice(1).toLowerCase());
}
