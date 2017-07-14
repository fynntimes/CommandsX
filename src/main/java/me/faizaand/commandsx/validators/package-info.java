// @formatter:off
/**
 * Validators are a convenient way to ensure users don't enter unacceptable values for commands.
 * The format for using a validator is <code>[name=value]</code>. You can see the names and value types
 * for each validator here.
 * <p>
 * <table border="1">
 *     <th>
 *         <td>Name</td>
 *         <td>Value</td>
 *         <td>Description</td>
 *     </th>
 *     <tr>
 *         <td>min</td>
 *         <td>number</td>
 *         <td>The minimum value for a numeric argument. Applies to <code>longs, floats, doubles, ints, shorts, and bytes</code>.</td>
 *     </tr>
 *     <tr>
 *         <td>max</td>
 *         <td>number</td>
 *         <td>The maximum value for a numeric argument. Applies to <code>longs, floats, doubles, ints, shorts, and bytes</code>.</td>
 *     </tr>
 *     <tr>
 *         <td>minLen</td>
 *         <td>number</td>
 *         <td>The minimum length for a String argument.</td>
 *     </tr>
 *     <tr>
 *         <td>maxLen</td>
 *         <td>number</td>
 *         <td>The maximum length for a String argument.</td>
 *     </tr>
 * </table>
 */
// @formatter:on
package me.faizaand.commandsx.validators;