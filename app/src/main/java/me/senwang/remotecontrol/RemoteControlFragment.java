package me.senwang.remotecontrol;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class RemoteControlFragment extends Fragment {
	private TextView mSelectedTextView;
	private TextView mWorkingTextView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_remote_control,container, false);
		mSelectedTextView = (TextView) v.findViewById(R.id.selected_text_view);
		mWorkingTextView = (TextView) v.findViewById(R.id.working_text_view);

		View.OnClickListener numberButtonListener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String working = mWorkingTextView.getText().toString();

				TextView textView = (TextView) v;
				String text = textView.getText().toString();

				if (working.equals("0")) {
					mWorkingTextView.setText(text);
				} else {
					mWorkingTextView.setText(working + text);
				}
			}
		};

		TableLayout tableLayout = (TableLayout) v.findViewById(R.id.table_layout);
		int number = 1;
		for (int i = 2; i < tableLayout.getChildCount() - 1; i++) {
			TableRow row = (TableRow) tableLayout.getChildAt(i);
			for (int j = 0; j < row.getChildCount(); j++) {
				Button button = (Button) row.getChildAt(j);
				button.setText(String.valueOf(number));
				button.setOnClickListener(numberButtonListener);
				number++;
			}
		}

		TableRow buttomRow = (TableRow) tableLayout.getChildAt(tableLayout.getChildCount() - 1);

		Button deleteButton = (Button) buttomRow.getChildAt(0);
		deleteButton.setText("Delete");
		deleteButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mWorkingTextView.setText("0");
			}
		});

		Button zeroButton = (Button) buttomRow.getChildAt(1);
		zeroButton.setText("0");
		zeroButton.setOnClickListener(numberButtonListener);

		Button enterButton = (Button) buttomRow.getChildAt(2);
		enterButton.setText("Enter");
		enterButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				CharSequence working = mWorkingTextView.getText();
				if (working.length() > 0) {
					mSelectedTextView.setText(working);
					mWorkingTextView.setText("0");
				}
			}
		});

		return v;
	}
}
