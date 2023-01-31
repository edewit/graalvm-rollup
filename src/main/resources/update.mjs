import format from 'date-fns/format';

export default function update() {
    console.log(format(new Date(), 'h:mm:ssa'));
	setTimeout(update, 1000);
}
